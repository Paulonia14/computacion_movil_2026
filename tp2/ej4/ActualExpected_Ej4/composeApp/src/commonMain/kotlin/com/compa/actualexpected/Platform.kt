package com.compa.actualexpected

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform