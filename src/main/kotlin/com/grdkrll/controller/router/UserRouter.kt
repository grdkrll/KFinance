package com.grdkrll.controller.router

import com.grdkrll.controller.handler.UserHandler
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.userRouting() {
    val handler by inject<UserHandler>()

    post("/sign_up") { handler.signUp(this.call) }
    post("/sign_in") { handler.signIn(this.call) }
    post("/google_sign_in") { handler.signInWithGoogle(this.call) }
    authenticate("jwt") {
        get("/{handle}") { handler.getByHandle(this.call) }
        delete("/{handle}") { handler.deleteByHandle(this.call) }
    }
}