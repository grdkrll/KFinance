package com.grdkrll.model.dto

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

/**
 * An Interface of an API Response used for Messages and Exceptions
 */
interface ApiResponse {
    val status: HttpStatusCode
    val message: String

    suspend fun respond(call: ApplicationCall) = call.respond(status, this)
}