package com.lightningkite.kommunicate

import com.lightningkite.mirror.info.Type
import com.lightningkite.mirror.serialization.ByteArraySerializer
import com.lightningkite.mirror.serialization.StringSerializer
import com.lightningkite.mirror.serialization.json.JsonSerializer

fun <T> HttpBody.Companion.serialize(
    value: T,
    type: Type<T>,
    serializer: StringSerializer
) = HttpBody.string(serializer.contentType, serializer.write(value, type))

fun <T> HttpBody.Companion.serialize(
    value: T,
    type: Type<T>,
    serializer: ByteArraySerializer
) = HttpBody.byteArray(serializer.contentType, serializer.write(value, type))


suspend fun <T> HttpClient.callSerializer(
    url: String,
    method: HttpMethod,
    body: HttpBody = HttpBody.EMPTY,
    headers: Map<String, List<String>> = mapOf(),
    type: Type<T>,
    serializer: StringSerializer
): T = callSerializerDetail(url, method, body, headers, type, serializer).let {
    if (it.failure != null) throw it.failure!!
    else it.result!!
}

suspend fun <T> HttpClient.callSerializerDetail(
    url: String,
    method: HttpMethod,
    body: HttpBody = HttpBody.EMPTY,
    headers: Map<String, List<String>> = mapOf(),
    type: Type<T>,
    serializer: StringSerializer
) = callStringDetail(
    url,
    method,
    body,
    headers
).copy { serializer.read(it, type) }

suspend fun <T> HttpClient.callSerializer(
    url: String,
    method: HttpMethod,
    body: HttpBody = HttpBody.EMPTY,
    headers: Map<String, List<String>> = mapOf(),
    type: Type<T>,
    serializer: ByteArraySerializer
): T = callSerializerDetail(url, method, body, headers, type, serializer).let {
    if (it.failure != null) throw it.failure!!
    else it.result!!
}

suspend fun <T> HttpClient.callSerializerDetail(
    url: String,
    method: HttpMethod,
    body: HttpBody = HttpBody.EMPTY,
    headers: Map<String, List<String>> = mapOf(),
    type: Type<T>,
    serializer: ByteArraySerializer
) = callByteArrayDetail(
    url,
    method,
    body,
    headers
).copy { serializer.read(it, type) }