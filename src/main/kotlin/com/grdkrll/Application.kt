package com.grdkrll

import com.grdkrll.config.configureDatabase
import com.grdkrll.plugins.configureKoin
import com.grdkrll.plugins.configureSecurity
import io.ktor.server.application.*
import com.grdkrll.plugins.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureSecurity()
    configureSerialization()
    configureKoin()
    configureDatabase()
    configureRouting()
    configureStatusPages()
}
