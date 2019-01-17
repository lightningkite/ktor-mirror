package com.lightningkite.mirror.server

import com.lightningkite.kommon.native.SharedImmutable
import com.lightningkite.mirror.info.*
import kotlin.reflect.KClass

@SharedImmutable
val TestRegistry = ClassInfoRegistry(
    com.lightningkite.mirror.request.RequestClassInfo,
    mirror.kotlin.ComparatorClassInfo,
    com.lightningkite.mirror.archive.database.SuspendMapEntryClassInfo,
    com.lightningkite.mirror.archive.database.SuspendMapGetRequestClassInfo,
    com.lightningkite.mirror.archive.database.SuspendMapModifyRequestClassInfo,
    com.lightningkite.mirror.archive.database.SuspendMapPutRequestClassInfo,
    com.lightningkite.mirror.archive.database.SuspendMapQueryRequestClassInfo,
    com.lightningkite.mirror.archive.database.SuspendMapRemoveRequestClassInfo,
    com.lightningkite.mirror.archive.database.SuspendMapServerFunctionClassInfo,
    com.lightningkite.mirror.archive.model.ConditionAlwaysClassInfo,
    com.lightningkite.mirror.archive.model.ConditionAndClassInfo,
    com.lightningkite.mirror.archive.model.ConditionEqualClassInfo,
    com.lightningkite.mirror.archive.model.ConditionEqualToOneClassInfo,
    com.lightningkite.mirror.archive.model.ConditionGreaterThanClassInfo,
    com.lightningkite.mirror.archive.model.ConditionGreaterThanOrEqualClassInfo,
    com.lightningkite.mirror.archive.model.ConditionLessThanClassInfo,
    com.lightningkite.mirror.archive.model.ConditionLessThanOrEqualClassInfo,
    com.lightningkite.mirror.archive.model.ConditionNeverClassInfo,
    com.lightningkite.mirror.archive.model.ConditionNotClassInfo,
    com.lightningkite.mirror.archive.model.ConditionNotEqualClassInfo,
    com.lightningkite.mirror.archive.model.ConditionOrClassInfo,
    com.lightningkite.mirror.archive.model.ConditionRegexTextSearchClassInfo,
    com.lightningkite.mirror.archive.model.ConditionTextSearchClassInfo,
    com.lightningkite.mirror.archive.model.ConditionClassInfo,
    com.lightningkite.mirror.archive.model.HasIdClassInfo,
    com.lightningkite.mirror.archive.model.IdClassInfo,
    com.lightningkite.mirror.archive.model.OperationSetClassInfo,
    com.lightningkite.mirror.archive.model.OperationClassInfo,
    com.lightningkite.mirror.archive.model.ReferenceClassInfo,
    com.lightningkite.mirror.archive.model.SortClassInfo,
    com.lightningkite.lokalize.time.TimeStampClassInfo,
    com.lightningkite.kommunicate.mirror.RemoteExceptionDataClassInfo,
    com.lightningkite.mirror.archive.server.security.HasPasswordClassInfo,
    com.lightningkite.mirror.server.ThrowExceptionRequestClassInfo,
    com.lightningkite.mirror.server.UserClassInfo,
    com.lightningkite.mirror.server.UserRoleClassInfo,
    com.lightningkite.mirror.server.UserSessionClassInfo,
    com.lightningkite.mirror.server.UserGetClassInfo,
    com.lightningkite.mirror.server.UserPutClassInfo,
    com.lightningkite.mirror.server.UserModifyClassInfo,
    com.lightningkite.mirror.server.UserRemoveClassInfo,
    com.lightningkite.mirror.server.UserQueryClassInfo,
    com.lightningkite.mirror.server.UserResetPasswordClassInfo,
    com.lightningkite.mirror.server.UserLoginClassInfo
)