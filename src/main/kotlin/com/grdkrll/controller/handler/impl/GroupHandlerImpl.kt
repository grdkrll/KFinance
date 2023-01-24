package com.grdkrll.controller.handler.impl

import com.grdkrll.controller.handler.GroupHandler
import com.grdkrll.model.dto.group.request.GroupRequest
import com.grdkrll.model.dto.group.request.MemberRequest
import com.grdkrll.service.GroupService
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GroupHandlerImpl : GroupHandler, KoinComponent {
    private val service by inject<GroupService>()

    override suspend fun createGroup(call: ApplicationCall) {
        val request = call.receive<GroupRequest>()
        service.createGroup(request)
    }

    override suspend fun addMember(call: ApplicationCall) {
        val request = call.receive<MemberRequest>()
        val response = service.addMember(request)
        call.respond(response)
    }

    override suspend fun removeMember(call: ApplicationCall) {
        val request = call.receive<MemberRequest>()
        val response = service.removeMember(request)
        call.respond(response)
    }
}