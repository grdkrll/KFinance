package com.grdkrll.plugins

import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.grdkrll.util.JwtService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

/**
 * An Extension Function used to configure Authentication via JWT Bearer Token for the API
 */
fun Application.configureSecurity() {
    authentication {
        jwt {
            val jwtAudience = this@configureSecurity.environment.config.property("jwt.audience").getString()
            realm = this@configureSecurity.environment.config.property("jwt.realm").getString()
            verifier(
                JWT
                    .require(Algorithm.HMAC256("secret"))
                    .withAudience(jwtAudience)
                    .withIssuer(this@configureSecurity.environment.config.property("jwt.domain").getString())
                    .build()
            )
            validate { credential ->
                if (credential.payload.audience.contains(jwtAudience)) JWTPrincipal(credential.payload) else null
            }
        }
    }
    authentication {
        jwt("jwt") {
            verifier(JwtService.verifier())
            validate { credential -> JwtService.validate(credential) }
            challenge { _, _ ->
                call.respond(HttpStatusCode.Unauthorized, "Token is not valid or has expired")
            }
        }
    }

}
