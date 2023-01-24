package com.grdkrll.service.impl

import com.grdkrll.model.dao.Group
import com.grdkrll.model.dao.User
import com.grdkrll.model.dao.UserGroup
import com.grdkrll.model.dto.exception.group.GroupNotFoundException
import com.grdkrll.model.dto.exception.user.UserNotFoundException
import com.grdkrll.model.dto.group.request.GroupRequest
import com.grdkrll.model.dto.group.request.MemberRequest
import com.grdkrll.model.dto.group.response.GroupResponse
import com.grdkrll.model.table.Groups
import com.grdkrll.model.table.Users
import com.grdkrll.model.table.UsersGroups
import com.grdkrll.service.GroupService
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.transaction

class GroupServiceImpl : GroupService {

    override fun createGroup(request: GroupRequest) {
        return transaction {
            val groupOwner = User.find {Users.handle eq request.owner }.singleOrNull() ?: throw UserNotFoundException()
            val group = Group.new {
                name = request.name
                owner = groupOwner.id
                handle = request.handle
            }
            addMember(MemberRequest(request.owner, request.handle))
        }
    }
    override fun addMember(request: MemberRequest) : GroupResponse {
        return transaction {
            val userId = User.find { Users.handle eq request.userHandle}.singleOrNull()?.id ?: throw UserNotFoundException()
            val targetGroup = Group.find { Groups.handle eq request.groupHandle}.singleOrNull() ?: throw GroupNotFoundException()
            UserGroup.new {
                user = userId
                group = targetGroup.id
            }
            GroupResponse(targetGroup)
        }
    }

    override fun removeMember(request: MemberRequest) : GroupResponse {
        return transaction {
            val userId = User.find { Users.handle eq request.userHandle}.singleOrNull()?.id ?: throw UserNotFoundException()
            val targetGroup = Group.find { Groups.handle eq request.groupHandle}.singleOrNull() ?: throw GroupNotFoundException()
            if(UserGroup.find {UsersGroups.user eq userId and (UsersGroups.group eq targetGroup.id)}.empty()) throw UserNotFoundException()
            UsersGroups.deleteWhere {user eq userId and (group eq targetGroup.id)}
            GroupResponse(targetGroup)
        }
    }
}