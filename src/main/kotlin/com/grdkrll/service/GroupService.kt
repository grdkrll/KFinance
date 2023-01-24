package com.grdkrll.service

import com.grdkrll.model.dto.group.request.GroupRequest
import com.grdkrll.model.dto.group.request.MemberRequest
import com.grdkrll.model.dto.group.response.GroupResponse

interface GroupService {
    fun createGroup(request: GroupRequest)
    fun addMember(request: MemberRequest) : GroupResponse
    fun removeMember(request: MemberRequest) : GroupResponse
}