package com.grdkrll.service.impl

import com.grdkrll.model.dao.User
import com.grdkrll.model.dto.exception.user.PasswordNotMatchException
import com.grdkrll.model.dto.exception.user.UnauthorizedException
import com.grdkrll.model.dto.exception.user.UserAlreadyExistsException
import com.grdkrll.model.dto.exception.user.UserNotFoundException
import com.grdkrll.model.dto.user.request.UserSignInRequest
import com.grdkrll.model.dto.user.request.UserSignUpRequest
import com.grdkrll.model.dto.user.response.UserResponse
import com.grdkrll.model.dto.user.response.UserSignResponse
import com.grdkrll.model.table.Users
import com.grdkrll.service.UserService
import com.grdkrll.util.JwtService
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.or
import org.jetbrains.exposed.sql.transactions.transaction
import org.mindrot.jbcrypt.BCrypt

class UserServiceImpl : UserService {
    override fun signUp(request: UserSignUpRequest): UserSignResponse {
        return transaction {
            if(!User.find {Users.email eq request.email }.empty()) throw UserAlreadyExistsException()
            val user = User.new {
                email = request.email
                handle = request.handle
                passwordHash = BCrypt.hashpw(request.password, BCrypt.gensalt())
            }
            val token = JwtService.generate(user.id.value, user.email)
            UserSignResponse(token, user)
        }
    }

    override fun signIn(request: UserSignInRequest): UserSignResponse {
        return transaction {
            val user = User.find { Users.email eq request.login or (Users.handle eq request.login) }.singleOrNull()?: throw UserNotFoundException()
            if(!BCrypt.checkpw(request.password, user.passwordHash)) throw PasswordNotMatchException()
            val token = JwtService.generate(user.id.value, user.email)
            UserSignResponse(token, user)
        }
    }

    override fun getByHandle(handle: String): UserResponse {
        return transaction {
            val user = User.find {Users.handle eq handle}.singleOrNull() ?: throw UserNotFoundException()
            UserResponse(user)
        }
    }

    override fun deleteByHandle(handle: String, id: Int): UserResponse {
        return transaction {
            val user = User.find { Users.handle eq handle}.singleOrNull() ?: throw UserNotFoundException()
            if(user.id.value != id) throw UnauthorizedException()
            Users.deleteWhere {Users.id eq id }
            UserResponse(user)
        }
    }

}