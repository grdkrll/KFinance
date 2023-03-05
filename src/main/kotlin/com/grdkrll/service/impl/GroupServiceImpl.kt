package com.grdkrll.service.impl

import com.grdkrll.model.dao.Group
import com.grdkrll.model.dao.User
import com.grdkrll.model.dao.UserGroup
import com.grdkrll.model.dto.exception.group.GroupNotFoundException
import com.grdkrll.model.dto.exception.user.UserNotFoundException
import com.grdkrll.model.dto.group.request.GroupRequest
import com.grdkrll.model.dto.group.request.MemberRequest
import com.grdkrll.model.dto.group.response.GroupResponse
import com.grdkrll.model.table.UsersGroups
import com.grdkrll.service.GroupService
import com.grdkrll.util.UserSession
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.transaction

class GroupServiceImpl : GroupService {
    override fun createGroup(session: UserSession, request: GroupRequest) : GroupResponse {
        return transaction {
            val userId = User.findById(session.id)?.id ?: throw UserNotFoundException()
            val group = Group.new {
                name = request.name
                ownerId = userId
                handle = request.handle
            }
            addMember(session, MemberRequest(group.id.value))
            GroupResponse(group)
        }
    }
    override fun addMember(session: UserSession, request: MemberRequest) : GroupResponse {
        return transaction {
            val targetGroup = Group.findById(request.groupId) ?: throw GroupNotFoundException()
            UserGroup.new {
                user = User.findById(session.id)?.id ?: throw UserNotFoundException()
                group = targetGroup.id
            }
            GroupResponse(targetGroup)
        }
    }

    override fun removeMember(session: UserSession, request: MemberRequest) : GroupResponse {
        return transaction {
            val userId = User.findById(session.id)?.id ?: throw UserNotFoundException()
            val targetGroup = Group.findById(request.groupId) ?: throw GroupNotFoundException()
            if(UserGroup.find {UsersGroups.user eq userId and (UsersGroups.group eq targetGroup.id)}.empty()) throw UserNotFoundException()
            UsersGroups.deleteWhere {user eq userId and (group eq targetGroup.id)}
            GroupResponse(targetGroup)
        }
    }
}