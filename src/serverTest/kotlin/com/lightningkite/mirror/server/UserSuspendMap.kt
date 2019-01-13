package com.lightningkite.mirror.server

import com.lightningkite.kommon.exception.ForbiddenException
import com.lightningkite.mirror.archive.database.secure.SecureSuspendMap
import com.lightningkite.mirror.archive.model.Condition
import com.lightningkite.mirror.archive.model.Id
import com.lightningkite.mirror.archive.model.Operation
import com.lightningkite.mirror.archive.server.security.HasPassword
import com.lightningkite.mirror.info.FieldInfo
import com.lightningkite.mirror.info.type
import com.lightningkite.mirror.request.server.ServerFunctionHandler
import com.lightningkite.mirror.request.server.suspendMap

val UserSuspendMap = SecureSuspendMap<Id, User, User>(
        underlying = Settings.provider.suspendMap(Id::class.type, User::class.type),
        rules = HasPassword.rules()
)

fun ServerFunctionHandler<User>.userSuspendMapSetup() {
    suspendMap(UserSuspendMap) {
        get<User.Get>()
        put<User.Put>()
        modify<User.Modify>()
        remove<User.Remove>()
        query<User.Query>()
        invocation(User.Login::class) {
            val u = UserSuspendMap.underlying.find(
                    condition = Condition.Field(
                            field = UserClassInfo.fieldEmail,
                            condition = Condition.Equal(email)
                    )
            ) ?: throw ForbiddenException()
            User.Session(
                    user = u.value,
                    token = Tokens.generate(u.value)
            )
        }
        invocation(User.ResetPassword::class){
            val u = UserSuspendMap.underlying.find(
                    condition = Condition.Field(
                            field = UserClassInfo.fieldEmail,
                            condition = Condition.Equal(email)
                    )
            ) ?: throw ForbiddenException()
            val newPassword = (1..20).joinToString("") { ('a'..'z').random().toString() }
            UserSuspendMap.underlying.modify(
                    key = u.key,
                    operation = Operation.Fields(
                            classInfo = UserClassInfo,
                            changes = mapOf<FieldInfo<User, *>, Operation<*>>(
                                    UserClassInfo.fieldPassword to Operation.Set(newPassword)
                            )
                    )
            )
        }
    }
}
