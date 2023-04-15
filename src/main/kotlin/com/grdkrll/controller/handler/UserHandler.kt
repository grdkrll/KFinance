package com.grdkrll.controller.handler

import io.ktor.server.application.*

interface UserHandler {
    suspend fun signUp(call: ApplicationCall)
    suspend fun signIn(call: ApplicationCall)
    suspend fun getByHandle(call: ApplicationCall)
    suspend fun deleteByHandle(call: ApplicationCall)
    suspend fun signInWithGoogle(call: ApplicationCall)
    suspend fun changeData(call: ApplicationCall)

}