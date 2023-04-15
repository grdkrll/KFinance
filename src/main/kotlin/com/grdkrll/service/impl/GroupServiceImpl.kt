package com.grdkrll.service.impl

import com.grdkrll.model.dao.Group
import com.grdkrll.model.dao.User
import com.grdkrll.model.dao.UserGroup
import com.grdkrll.model.dto.exception.group.GroupNotFoundException
import com.grdkrll.model.dto.exception.user.PasswordNotMatchException
import com.grdkrll.model.dto.exception.user.UserNotFoundException
import com.grdkrll.model.dto.group.request.GroupRequest
import com.grdkrll.model.dto.group.request.MemberRequest
import com.grdkrll.model.dto.group.response.GroupResponse
import com.grdkrll.model.table.Groups
import com.grdkrll.model.table.UsersGroups
import com.grdkrll.service.GroupService
import com.grdkrll.util.UserSession
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.transaction
import org.mindrot.jbcrypt.BCrypt

class GroupServiceImpl : GroupService {
    override fun createGroup(session: UserSession, request: GroupRequest): GroupResponse {
        return transaction {
            val userId = User.findById(session.id)?.id ?: throw UserNotFoundException()
            val group = Group.new {
                name = request.name
                ownerId = userId
                handle = request.handle
                passwordHash = BCrypt.hashpw(request.password, BCrypt.gensalt())
            }
            addMember(session, MemberRequest(group.handle, request.password))
            GroupResponse(group)
        }
    }

    override fun addMember(session: UserSession, request: MemberRequest): GroupResponse {
        return transaction {
            val targetGroup =
                Group.find { Groups.handle eq request.handle }.singleOrNull() ?: throw GroupNotFoundException()
            if (BCrypt.checkpw(request.password, targetGroup.passwordHash)) {
                UserGroup.new {
                    user = User.findById(session.id)?.id ?: throw UserNotFoundException()
                    group = targetGroup.id
                }
                GroupResponse(targetGroup)
            } else {
                throw PasswordNotMatchException()
            }
        }
    }

    override fun removeMember(session: UserSession, request: MemberRequest): GroupResponse {
        return transaction {
            val userId = User.findById(session.id)?.id ?: throw UserNotFoundException()
            val targetGroup =
                Group.find { Groups.handle eq request.handle }.singleOrNull() ?: throw GroupNotFoundException()
            if (BCrypt.checkpw(request.password, targetGroup.passwordHash)) {
                if (UserGroup.find { UsersGroups.user eq userId and (UsersGroups.group eq targetGroup.id) }
                        .empty()) throw UserNotFoundException()
                UsersGroups.deleteWhere { user eq userId and (group eq targetGroup.id) }
                GroupResponse(targetGroup)
            } else {
                throw PasswordNotMatchException()
            }
        }
    }

    override fun getAllGroupsOfUser(session: UserSession): List<GroupResponse> {
        return transaction {
            val userId = User.findById(session.id)?.id ?: throw UserNotFoundException()
            val res = mutableListOf<GroupResponse>()
            UserGroup.find { UsersGroups.user eq userId }
                .forEach { res.add(GroupResponse(Group.findById(it.group.value) ?: throw Exception())) }
            res
        }
    }
}