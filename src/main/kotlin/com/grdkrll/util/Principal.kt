package com.grdkrll.util

import com.grdkrll.model.dto.exception.user.UnauthorizedException
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

data class UserSession(val id: Int, val email: String)

fun ApplicationCall.getClaim(name: String) =
    principal<JWTPrincipal>()?.payload?.getClaim(name) ?: throw UnauthorizedException()

fun ApplicationCall.getSession() = UserSession(
    id = getClaim("id").asInt(),
    email = getClaim("email").asString()
)