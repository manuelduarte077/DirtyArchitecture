package dev.donmanuel.ktorexample

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform