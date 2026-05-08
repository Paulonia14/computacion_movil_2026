package com.compa.navigator

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform