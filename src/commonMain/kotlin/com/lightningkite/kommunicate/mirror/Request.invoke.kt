package com.lightningkite.kommunicate.mirror

import com.lightningkite.kommunicate.HttpBody
import com.lightningkite.kommunicate.HttpClient
import com.lightningkite.kommunicate.HttpException
import com.lightningkite.kommunicate.HttpMethod
import com.lightningkite.mirror.info.Type
import com.lightningkite.mirror.info.TypeProjection
import com.lightningkite.mirror.info.type
import com.lightningkite.mirror.request.Request
import com.lightningkite.mirror.request.returnType
import com.lightningkite.mirror.serialization.ByteArraySerializer
import com.lightningkite.mirror.serialization.SerializationException
import com.lightningkite.mirror.serialization.StringSerializer

suspend fun <T> Request<T>.invoke(
        onEndpoint: String,
        headers: Map<String, List<String>> = mapOf(),
        serializer: StringSerializer
): T {
    return HttpClient.callStringDetail(
            onEndpoint,
            HttpMethod.POST,
            HttpBody.string(serializer.contentType, serializer.write(this, Type(Request::class, listOf(TypeProjection.STAR))).also { println("Serialized: " + it) }),
            headers
    ).let {
        it.failure?.let { it as? HttpException }?.let {
            throw RemoteExceptionData.Thrown(serializer.read(it.message!!, RemoteExceptionData::class.type))
        }
        try {
            val returnType = this::class.returnType(serializer.registry.classInfoRegistry)
            serializer.read(it.result!!, returnType)
        } catch (e: Exception) {
            throw SerializationException("Failed to read $it", e)
        }
    }
}

suspend fun <T> Request<T>.invoke(
        onEndpoint: String,
        headers: Map<String, List<String>> = mapOf(),
        serializer: ByteArraySerializer
): T {
    return HttpClient.callByteArrayDetail(
            onEndpoint,
            HttpMethod.POST,
            HttpBody.byteArray(serializer.contentType, serializer.write(this, Type(Request::class, listOf(TypeProjection.STAR)))),
            headers
    ).let {
        it.failure?.let { it as? HttpException }?.let {
            //TODO: Catch 'n throw
        }
        try {
            val returnType = this::class.returnType(serializer.registry.classInfoRegistry)
            serializer.read(it.result!!, returnType)
        } catch (e: Exception) {
            throw SerializationException("Failed to read $it", e)
        }
    }
}