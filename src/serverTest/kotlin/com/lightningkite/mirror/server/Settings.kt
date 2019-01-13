package com.lightningkite.mirror.server

import com.lightningkite.mirror.archive.database.RAMSuspendMap
import com.lightningkite.mirror.archive.database.SuspendMapProvider

object Settings {
    var provider: SuspendMapProvider = RAMSuspendMap.Provider
}