package com.grdkrll.controller.handler

import io.ktor.server.application.*

/**
 * A Handler Class used to handle requests about Groups
 */
interface GroupHandler {
    /**
     * Used to create a new Group
     */
    suspend fun createGroup(call: ApplicationCall)

    /**
     * Used to add a new Member to / join in the Group
     */
    suspend fun addMember(call: ApplicationCall)

    /**
     * Used to leave the Group
     */
    suspend fun leaveGroup(call: ApplicationCall)

    /**
     * Used to get a List of All Groups of the User
     */
    suspend fun getAllGroupsOfUser(call: ApplicationCall)

    /**
     * Used to get All Members of the Group
     */
    suspend fun getAllMembers(call: ApplicationCall)

    /**
     * Used to remove a Member from the Group
     */
    suspend fun removeMember(call: ApplicationCall)

    /**
     * Used to change information about the Group
     */
    suspend fun changeGroup(call: ApplicationCall)
}