package reflected

import com.lightningkite.mirror.info.*
import kotlin.reflect.KClass

fun configureMirror() {
    ClassInfo.register(com.lightningkite.kommunicate.PostClassInfo)
}