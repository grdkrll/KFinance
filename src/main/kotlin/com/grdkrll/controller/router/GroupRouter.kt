package com.grdkrll.controller.router

import com.grdkrll.controller.handler.GroupHandler
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

/**
 * A Router Function used to route calls to the API about Groups to specific functions of [GroupHandler]
 */
fun Route.groupRouting() {
    val handler by inject<GroupHandler>()

    /**
     * Used to indicate that calls listed below require authentication via JWT Bearer Token
     */
    authenticate("jwt") {
        post("/create_group") { handler.createGroup(this.call) }
        post("/add_member") { handler.addMember(this.call) }
        post("/leave") { handler.leaveGroup(this.call) }
        get("/get_all") { handler.getAllGroupsOfUser(this.call) }
        post("/change_group") { handler.changeGroup(this.call) }
        get("/get_all_members") { handler.getAllMembers(this.call) }
        post("/remove_member") { handler.removeMember(this.call) }
    }
}