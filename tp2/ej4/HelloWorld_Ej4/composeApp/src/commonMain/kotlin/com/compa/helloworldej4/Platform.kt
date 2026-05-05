package com.compa.helloworldej4

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform