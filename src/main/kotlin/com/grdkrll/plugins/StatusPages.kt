package com.grdkrll.plugins

import com.grdkrll.model.dto.exception.AbstractApiException
import com.grdkrll.model.dto.exception.UnexpectedException
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*

/**
 * An Extension Function used to configure Exception Messages of the API
 */
fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<AbstractApiException> { call, cause ->
            println(cause.stackTraceToString())
            cause.respond(call)
        }
        exception<Throwable> { call, cause ->
            println(cause.stackTraceToString())
            UnexpectedException().respond(call)
        }
    }
}