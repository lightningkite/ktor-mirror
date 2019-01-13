package com.lightningkite.kommunicate.mirror

import com.lightningkite.kommunicate.HttpBody
import com.lightningkite.kommunicate.HttpClient
import com.lightningkite.kommunicate.HttpException
import com.lightningkite.kommunicate.HttpMethod
import com.lightningkite.mirror.info.Type
import com.lightningkite.mirror.info.TypeProjection
import com.lightningkite.mirror.info.type
import com.lightningkite.mirror.request.Request
import com.lightningkite.mirror.request.RequestHandler
import com.lightningkite.mirror.request.returnType
import com.lightningkite.mirror.serialization.SerializationException
import com.lightningkite.mirror.serialization.StringSerializer

class RemoteRequestHandler(
        val onEndpoint: String,
        val headers: Map<String, List<String>>,
        val serializer: StringSerializer
): RequestHandler {
    override suspend fun <T> invoke(request: Request<T>): T {
        return HttpClient.callStringDetail(
                onEndpoint,
                HttpMethod.POST,
                HttpBody.string(serializer.contentType, serializer.write(request, Type(Request::class, listOf(TypeProjection.STAR))).also { println("Serialized: " + it) }),
                headers
        ).let {
            it.failure?.let { it as? HttpException }?.let {
                throw RemoteExceptionData.Thrown(serializer.read(it.message!!, RemoteExceptionData::class.type))
            }
            try {
                val returnType = request::class.returnType(serializer.registry.classInfoRegistry)
                serializer.read(it.result!!, returnType)
            } catch (e: Exception) {
                throw SerializationException("Failed to read $it", e)
            }
        }
    }
}