package com.compa.ktorbasic

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform