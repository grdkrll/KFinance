package com.grdkrll.service

import com.grdkrll.model.dto.group.request.GroupRequest
import com.grdkrll.model.dto.group.request.MemberRequest
import com.grdkrll.model.dto.group.response.GroupResponse
import com.grdkrll.util.UserSession

interface GroupService {
    fun createGroup(session: UserSession, request: GroupRequest) : GroupResponse
    fun addMember(session: UserSession, request: MemberRequest) : GroupResponse
    fun removeMember(session: UserSession, request: MemberRequest) : GroupResponse
    fun getAllGroupsOfUser(session: UserSession) : List<GroupResponse>
}