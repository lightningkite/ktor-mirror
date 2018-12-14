package com.lightningkite.kommunicate

import com.lightningkite.mirror.info.list
import com.lightningkite.mirror.info.type
import com.lightningkite.mirror.serialization.DefaultRegistry
import com.lightningkite.mirror.serialization.json.JsonSerializer
import kotlinx.coroutines.*
import platform.Foundation.*
import reflected.TestRegistry
import kotlin.test.Test

class JsonTest {

    val serializer = JsonSerializer(DefaultRegistry + TestRegistry)

    @Test
    fun testGet() {
        var done = false
        GlobalScope.launch(Dispatchers.Unconfined) {
            println(
                    HttpClient.callSerializer(
                            url = "https://jsonplaceholder.typicode.com/posts",
                            method = HttpMethod.GET,
                            serializer = serializer,
                            type = Post::class.type.list
                    )
            )
            done = true
        }
        println("Escaped the scope $done")
        while (!done) {
            println("LoopStart")
            NSRunLoop.mainRunLoop.runMode(NSDefaultRunLoopMode, beforeDate = NSDate.create(timeInterval = 1.0, sinceDate = NSDate()))
            println("LoopEnd")
        }
    }

    @Test
    fun testPost() {
        var done = false
        GlobalScope.launch(Dispatchers.Unconfined) {
            println(
                    HttpClient.callSerializer(
                            url = "https://jsonplaceholder.typicode.com/posts",
                            method = HttpMethod.POST,
                            body = HttpBody.serialize(
                                    Post(
                                            title = "Arrogant Example Post",
                                            body = "Your posts should aspire to be like this one."
                                    ), Post::class.type,
                                    serializer = serializer
                            ),
                            serializer = serializer,
                            type = Post::class.type
                    )
            )
            done = true
        }
        println("Escaped the scope $done")
        while (!done) {
            println("LoopStart")
            NSRunLoop.mainRunLoop.runMode(NSDefaultRunLoopMode, beforeDate = NSDate.create(timeInterval = 1.0, sinceDate = NSDate()))
            println("LoopEnd")
        }
    }
}
