package com.grdkrll.plugins

import com.grdkrll.config.KoinSettings
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin

/**
 * An Extension Function used to configure Koin Dependency Injection Framework
 */
fun Application.configureKoin() {
    install(Koin) {
        modules(KoinSettings.services, KoinSettings.handlers)
    }
}