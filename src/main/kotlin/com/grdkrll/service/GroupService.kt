package com.grdkrll.service

import com.grdkrll.model.dto.group.request.*
import com.grdkrll.model.dto.group.response.GroupResponse
import com.grdkrll.model.dto.group.response.MemberResponse
import com.grdkrll.util.UserSession

interface GroupService {
    fun createGroup(session: UserSession, request: GroupRequest): GroupResponse
    fun addMember(session: UserSession, request: MemberRequest): GroupResponse
    fun leaveGroup(session: UserSession, request: MemberRequest): GroupResponse
    fun getAllGroupsOfUser(session: UserSession): List<GroupResponse>
    fun changeGroup(session: UserSession, request: ChangeGroupRequest): GroupResponse
    fun getAllMembers(session: UserSession, request: AllMembersRequest): List<MemberResponse>
    fun removeMember(session: UserSession, request: RemoveMemberRequest): GroupResponse
}