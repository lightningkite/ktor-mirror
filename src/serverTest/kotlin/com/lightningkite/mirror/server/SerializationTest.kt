package com.lightningkite.mirror.server

import com.lightningkite.mirror.info.Type
import com.lightningkite.mirror.info.type
import com.lightningkite.mirror.request.Request
import com.lightningkite.mirror.serialization.DefaultRegistry
import com.lightningkite.mirror.serialization.json.JsonSerializer
import kotlin.test.Test

class SerializationTest {

    val registry = DefaultRegistry + TestRegistry
    val serializer = JsonSerializer(registry)

    fun <T> test(value: T, type: Type<T>) {
        val result = serializer.write(value, type)
        println(result)
        val back = serializer.read(result, type)
    }

    @Test fun test(){
        val newUser = User(email = "josephivie@gmail.com", password = "testpasswordofnightmares")
        test(User.Put(newUser.id, newUser), User.Put::class.type)
        test(User.Put(newUser.id, newUser), Request::class.type)
    }
}