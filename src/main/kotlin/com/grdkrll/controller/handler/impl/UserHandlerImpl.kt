package com.grdkrll.controller.handler.impl

import com.grdkrll.controller.handler.UserHandler
import com.grdkrll.model.dto.exception.user.EmptyHandleException
import com.grdkrll.model.dto.user.request.GoogleSignInRequest
import com.grdkrll.model.dto.user.request.UserChangeDataRequest
import com.grdkrll.model.dto.user.request.UserSignInRequest
import com.grdkrll.model.dto.user.request.UserSignUpRequest
import com.grdkrll.service.UserService
import com.grdkrll.util.getSession
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UserHandlerImpl : UserHandler, KoinComponent {
    private val service by inject<UserService>()

    override suspend fun signUp(call: ApplicationCall) {
        val request = call.receive<UserSignUpRequest>()
        val response = service.signUp(request)
        call.respond(response)
    }

    override suspend fun signIn(call: ApplicationCall) {
        val request = call.receive<UserSignInRequest>()
        val response = service.signIn(request)
        call.respond(response)
    }

    override suspend fun getByHandle(call: ApplicationCall) {
        val handle = call.parameters["handle"] ?: throw EmptyHandleException()
        val response = service.getByHandle(handle)
        call.respond(response)
    }

    override suspend fun deleteByHandle(call: ApplicationCall) {
        val handle = call.parameters["handle"] ?: throw EmptyHandleException()
        val response = service.deleteByHandle(handle, call.getSession().id)
        call.respond(response)
    }

    override suspend fun signInWithGoogle(call: ApplicationCall) {
        val token = call.receive<GoogleSignInRequest>().googleIdToken
        val response = service.signInWithGoogle(token)
        call.respond(response)
    }

    override suspend fun changeData(call: ApplicationCall) {
        val data = call.receive<UserChangeDataRequest>()
        val response = service.changeUserData(call.getSession(), data)
        call.respond(response)
    }
}