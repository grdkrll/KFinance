package com.grdkrll.plugins

import io.ktor.serialization.jackson.*
import com.fasterxml.jackson.databind.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.application.*

/**
 * An Extension Function used to configure Serialization via Jackson
 */
fun Application.configureSerialization() {
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }
}

