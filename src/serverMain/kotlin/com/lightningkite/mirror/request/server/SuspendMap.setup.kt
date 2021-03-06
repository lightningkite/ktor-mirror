package com.lightningkite.mirror.request.server

import com.lightningkite.mirror.archive.database.*
import com.lightningkite.mirror.archive.database.secure.SecureSuspendMap

class ServerFunctionHandlerSuspendMapBuilder<USER, K, V : Any>(
        val handler: ServerFunctionHandler<USER>,
        val suspendMapGet: (USER?)-> SuspendMap<K, V>
) {
    inline fun <reified SF: SuspendMapGetRequest<K, V>> get() = with(handler)  {
        SF::class.invocation = { user -> suspendMapGet(user).get(key) }
    }
    inline fun <reified SF: SuspendMapPutRequest<K, V>> put() = with(handler)  {
        SF::class.invocation = { user -> suspendMapGet(user).put(key, value, conditionIfExists, create) }
    }
    inline fun <reified SF: SuspendMapModifyRequest<K, V>> modify() = with(handler)  {
        SF::class.invocation = { user -> suspendMapGet(user).modify(key, operation, condition) }
    }
    inline fun <reified SF: SuspendMapRemoveRequest<K, V>> remove() = with(handler)  {
        SF::class.invocation = { user -> suspendMapGet(user).remove(key, condition) }
    }
    inline fun <reified SF: SuspendMapQueryRequest<K, V>> query() = with(handler)  {
        SF::class.invocation = { user -> suspendMapGet(user).query(condition, keyCondition, sortedBy, after, count) }
    }
    inline fun <reified SF: SuspendMapGetNewKeyRequest<K, V>> getNewKey() = with(handler)  {
        SF::class.invocation = { user -> suspendMapGet(user).getNewKey() }
    }
    inline fun <reified SF: SuspendMapFindRequest<K, V>> find() = with(handler)  {
        SF::class.invocation = { user -> suspendMapGet(user).find(condition, sortedBy) }
    }
    inline fun <reified SF: SuspendMapGetManyRequest<K, V>> getMany() = with(handler)  {
        SF::class.invocation = { user -> suspendMapGet(user).getMany(keys) }
    }
    inline fun <reified SF: SuspendMapPutManyRequest<K, V>> putMany() = with(handler)  {
        SF::class.invocation = { user -> suspendMapGet(user).putMany(map) }
    }
    inline fun <reified SF: SuspendMapRemoveManyRequest<K, V>> removeMany() = with(handler)  {
        SF::class.invocation = { user -> suspendMapGet(user).removeMany(keys) }
    }
}

fun <USER, K, V : Any> ServerFunctionHandler<USER>.suspendMap(
        suspendMapGet: SecureSuspendMap<K, V, USER>,
        setup: ServerFunctionHandlerSuspendMapBuilder<USER, K, V>.()->Unit
) = ServerFunctionHandlerSuspendMapBuilder(this) { suspendMapGet.forUser(it) }.apply(setup)