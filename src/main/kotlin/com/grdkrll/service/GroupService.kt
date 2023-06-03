package com.grdkrll.service

import com.grdkrll.model.dto.group.request.*
import com.grdkrll.model.dto.group.response.GroupResponse
import com.grdkrll.model.dto.group.response.MemberResponse
import com.grdkrll.util.UserSession

/**
 * A Service Class used for processing API calls about Groups
 */
interface GroupService {
    /**
     * Used to create a new Group and add it to the database
     *
     * @param session the User Session from the JWT Token
     * @param request the Request data to create the Group
     */
    fun createGroup(session: UserSession, request: GroupRequest): GroupResponse

    /**
     * Used to add a member to / join in a Group and register the inclusion in the database
     *
     * @param session the User Session from the JWT Token
     * @param request the Request data to add a new Member to the Group
     */
    fun addMember(session: UserSession, request: MemberRequest): GroupResponse

    /**
     * Used to leave a Group and register the removal in the database
     *
     * @param session the User Session from the JWT Token
     * @param request the Request data to remove the Member from the Group
     */
    fun leaveGroup(session: UserSession, request: MemberRequest): GroupResponse

    /**
     * Used to get a List of all Groups of a User
     *
     * @param session the User Session from the JWT Token
     */
    fun getAllGroupsOfUser(session: UserSession): List<GroupResponse>

    /**
     * Used to change information about a Group and register the change in the Database
     *
     * @param session the User Session from the JWT Token
     * @param request the Request data to change the Group
     */
    fun changeGroup(session: UserSession, request: ChangeGroupRequest): GroupResponse

    /**
     * Used to get a List of all Members of a Group
     *
     * @param session the User Session from the JWT Token
     * @param request the Request data of the Group
     */
    fun getAllMembers(session: UserSession, request: AllMembersRequest): List<MemberResponse>

    /**
     * Used to remove a Member from a Group and register the removal in the Database
     *
     * @param session the User Session from the JWT Token
     * @param request the Request data to remove the Member
     */
    fun removeMember(session: UserSession, request: RemoveMemberRequest): GroupResponse
}