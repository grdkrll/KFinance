package com.grdkrll.util

import com.grdkrll.model.dto.exception.user.UnauthorizedException
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

/**
 * A Data Class used to handle User Session via JWT Tokens
 *
 * @property id the id of the Signed-In User
 * @property email the email of the Signed-In User
 */
data class UserSession(val id: Int, val email: String)

/**
 * Used to get data from the JWT Token
 */
fun ApplicationCall.getClaim(name: String) =
    principal<JWTPrincipal>()?.payload?.getClaim(name) ?: throw UnauthorizedException()

/**
 * Used to get User Session from the JWT Token
 */
fun ApplicationCall.getSession() = UserSession(
    id = getClaim("id").asInt(),
    email = getClaim("email").asString()
)