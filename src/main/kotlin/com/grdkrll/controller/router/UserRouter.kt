package com.grdkrll.controller.router

import com.grdkrll.controller.handler.UserHandler
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

/**
 * A Router Function used to route calls to the API about Users to specific functions of [UserHandler]
 */
fun Route.userRouting() {
    val handler by inject<UserHandler>()

    post("/sign_up") { handler.signUp(this.call) }
    post("/sign_in") { handler.signIn(this.call) }
    post("/google_sign_in") { handler.signInWithGoogle(this.call) }

    /**
     * Used to indicate that calls listed below require authentication via JWT Bearer Token
     */
    authenticate("jwt") {   
        post("/change_data") { handler.changeData(this.call) }
    }
}