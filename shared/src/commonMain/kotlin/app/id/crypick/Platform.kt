package app.id.crypick

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform