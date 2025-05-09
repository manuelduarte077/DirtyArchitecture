package dev.donmanuel.todoapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform