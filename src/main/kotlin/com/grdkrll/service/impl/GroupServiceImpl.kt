package com.grdkrll.service.impl

import com.grdkrll.model.dao.Group
import com.grdkrll.model.dao.User
import com.grdkrll.model.dao.UserGroup
import com.grdkrll.model.dto.exception.group.GroupNotFoundException
import com.grdkrll.model.dto.exception.group.UserNotInGroupException
import com.grdkrll.model.dto.exception.user.PasswordNotMatchException
import com.grdkrll.model.dto.exception.user.UserNotFoundException
import com.grdkrll.model.dto.group.request.*
import com.grdkrll.model.dto.group.response.GroupResponse
import com.grdkrll.model.dto.group.response.MemberResponse
import com.grdkrll.model.table.Groups
import com.grdkrll.model.table.Users
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
        val response = transaction {
            val user = User.findById(session.id) ?: throw UserNotFoundException()
            val group = Group.new {
                name = request.name
                ownerId = user
                handle = request.handle
                passwordHash = BCrypt.hashpw(request.password, BCrypt.gensalt())
            }
            GroupResponse(group)
        }
        addMember(session, MemberRequest(request.handle, request.password))
        return response
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

    override fun leaveGroup(session: UserSession, request: MemberRequest): GroupResponse {
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

    override fun changeGroup(session: UserSession, request: ChangeGroupRequest): GroupResponse {
        return transaction {
            val userId = User.findById(session.id)?.id ?: throw UserNotFoundException()
            val targetGroup =
                Group.find { Groups.handle eq request.handle }.singleOrNull() ?: throw GroupNotFoundException()
            if (BCrypt.checkpw(request.confirmPassword, targetGroup.passwordHash)) {
                if (targetGroup.ownerId.id.value != userId.value) throw UserNotInGroupException()
                targetGroup.name = request.name
                targetGroup.handle = request.handle
                if (request.password.isNotEmpty()) {
                    targetGroup.passwordHash = BCrypt.hashpw(request.password, BCrypt.gensalt())
                }
            } else {
                throw PasswordNotMatchException()
            }
            GroupResponse(targetGroup)
        }
    }

    override fun getAllMembers(session: UserSession, request: AllMembersRequest): List<MemberResponse> {
        return transaction {
            val userId = User.findById(session.id)?.id ?: throw UserNotFoundException()
            val targetGroup =
                Group.find { Groups.handle eq request.handle }.singleOrNull() ?: throw GroupNotFoundException()
            if (targetGroup.ownerId.id != userId) {
                throw Exception("User is not the owner")
            }
            UserGroup.find { UsersGroups.user neq userId and (UsersGroups.group eq targetGroup.id) }
                .map { MemberResponse(User.findById(it.user) ?: throw UserNotFoundException()) }
        }
    }

    override fun removeMember(session: UserSession, request: RemoveMemberRequest): GroupResponse {
        return transaction {
            val userId = User.findById(session.id)?.id ?: throw UserNotFoundException()
            val targetGroup =
                Group.find { Groups.handle eq request.groupHandle }.singleOrNull() ?: throw GroupNotFoundException()
            if (targetGroup.ownerId.id != userId) {
                throw Exception("User is not the owner")
            }
            val targetUser =
                User.find { Users.handle eq request.userHandle }.singleOrNull() ?: throw UserNotFoundException()
            val userGroup =
                UserGroup.find { UsersGroups.user eq targetUser.id and (UsersGroups.group eq targetGroup.id) }
                    .singleOrNull() ?: throw Exception("User is not in the group")
            userGroup.delete()
            GroupResponse(targetGroup)
        }
    }
}