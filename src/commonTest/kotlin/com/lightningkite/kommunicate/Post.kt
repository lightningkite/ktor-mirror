package com.lightningkite.kommunicate

data class Post(
    var id: Long? = null,
    var userId: Long = 0,
    var title: String = "",
    var body: String = ""
)


//data class Post(
//    @Importance(0) var a: Long? = null,
//    @Importance(5) var b: Long = 0,
//    @Importance(8) var c: String = "",
//    @Importance(3) var d: String = ""
//)
