package com.grdkrll.util

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.typesafe.config.ConfigFactory
import io.ktor.server.auth.jwt.*
import io.ktor.server.config.*

object JwtService {
    private val config = HoconApplicationConfig(ConfigFactory.load())

    private val audience = config.property("jwt.audience").getString()
    private val secret = config.property("jwt.secret").getString()
    private val domain = config.property("jwt.domain").getString()

    fun verifier(): JWTVerifier = JWT.require(Algorithm.HMAC256(secret))
        .withAudience(audience)
        .withIssuer(domain)
        .build()

    fun validate(credential: JWTCredential): JWTPrincipal? {
        return if (credential.payload.getClaim("id").asInt() > 0)
            JWTPrincipal(credential.payload) else null
    }

    fun generate(id: Int, email: String): String = JWT.create()
        .withAudience(audience)
        .withIssuer(domain)
        .withClaim("id", id)
        .withClaim("email", email)
        .sign(Algorithm.HMAC256(secret))
}