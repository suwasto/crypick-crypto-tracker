package app.id.crypick.platform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform