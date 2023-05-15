package com.grdkrll.controller.handler

import io.ktor.server.application.*

interface GroupHandler {
    suspend fun createGroup(call: ApplicationCall)
    suspend fun addMember(call: ApplicationCall)
    suspend fun leaveGroup(call: ApplicationCall)
    suspend fun getAllGroupsOfUser(call: ApplicationCall)
    suspend fun getAllMembers(call: ApplicationCall)
    suspend fun removeMember(call: ApplicationCall)
    suspend fun changeGroup(call: ApplicationCall)
}