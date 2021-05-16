package com.example.gazeuslibrary

import org.koin.core.KoinApplication

class KoinInstance {

    object KoinContext {
        lateinit var koinApplication: KoinApplication
    }
}