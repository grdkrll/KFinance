package com.grdkrll.model.dto

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

interface ApiResponse {
    val status: HttpStatusCode
    val message: String

    suspend fun respond(call: ApplicationCall) = call.respond(status, this);
}