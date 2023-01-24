package com.grdkrll.controller.router

import com.grdkrll.controller.handler.GroupHandler
import com.grdkrll.controller.handler.UserHandler
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.groupRouting() {
    val handler by inject<GroupHandler>()

    authenticate("jwt") {
        post("/create_group") { handler.createGroup(this.call) }
        post("/add_member") { handler.addMember(this.call) }
        post("/remove_member") { handler.removeMember(this.call)}
    }
}