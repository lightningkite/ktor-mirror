package com.lightningkite.kommunicate

import com.lightningkite.mirror.info.list
import com.lightningkite.mirror.info.type
import com.lightningkite.mirror.serialization.json.JsonSerializer
import kotlinx.coroutines.runBlocking
import reflected.configureMirror
import kotlin.test.Test

class TestSerialization {
    @Test
    fun test() {
        runBlocking {

            configureMirror()

            val raw = HttpClient.callString(
                url = "https://jsonplaceholder.typicode.com/posts",
                method = HttpMethod.GET
            )
            println(raw)
            val parsed = JsonSerializer.read(raw, Post::class.type.list)
            println(parsed)

            //Let's grab a list of posts
            val posts: List<Post> = HttpClient.callSerializer(
                url = "https://jsonplaceholder.typicode.com/posts",
                method = HttpMethod.GET,
                type = Post::class.type.list
            )
            println(posts.joinToString("\n"))

            //Let's do a post!
            val result = HttpClient.callSerializer(
                url = "https://jsonplaceholder.typicode.com/posts",
                method = HttpMethod.POST,
                body = HttpBody.serialize(
                    Post(
                        title = "Arrogant Example Post",
                        body = "Your posts should aspire to be like this one."
                    ), Post::class.type
                ),
                type = Post::class.type
            )
            println(result)
        }
    }
}