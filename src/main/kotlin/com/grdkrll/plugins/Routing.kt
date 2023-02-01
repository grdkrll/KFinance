package com.grdkrll.plugins

import com.grdkrll.controller.router.groupRouting
import com.grdkrll.controller.router.transactionRouting
import com.grdkrll.controller.router.userRouting
import io.ktor.server.routing.*
import io.ktor.server.application.*
import com.grdkrll.model.dto.shared.HealthyMessage

fun Application.configureRouting() {

    routing {
        get("/check") { HealthyMessage().respond(call) }
        route("/u") { userRouting() }
        route("/g") { groupRouting() }
        route("/t") { transactionRouting() }
    }
}
