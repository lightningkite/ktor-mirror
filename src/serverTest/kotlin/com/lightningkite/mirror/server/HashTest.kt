package com.lightningkite.mirror.server

import com.lightningkite.mirror.archive.server.security.HasPassword
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class HashTest {

    @Test
    fun test() {

        val testUser = User(email = "test@test.com", password = "please hash me")

        runBlocking {
            UserSuspendMap.forUser(testUser).put(testUser.id, testUser.copy())
            val hashedResult = UserSuspendMap.underlying.get(testUser.id)
            val protectedResult = UserSuspendMap.forUser(testUser).get(testUser.id)
            assert(hashedResult?.password != testUser.password)
            assert(protectedResult?.password == HasPassword.HASHED_PLACEHOLDER)
        }
    }
}