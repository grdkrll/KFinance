package com.grdkrll.controller.handler

import com.grdkrll.model.dto.user.response.UserResponse
import io.ktor.server.application.*

/**
 * A Handler Class used to handle request about Users
 */
interface UserHandler {
    /**
     * Used to Sign Up the User
     */
    suspend fun signUp(call: ApplicationCall)

    /**
     * Used to Sign In the User
     */
    suspend fun signIn(call: ApplicationCall)

    /**
     * Used to get information about the User by their handle [UserResponse]
     */
    suspend fun getByHandle(call: ApplicationCall)

    /**
     * Used to delete the User Account by their handle [UserResponse]
     */
    suspend fun deleteByHandle(call: ApplicationCall)

    /**
     * Used to Sign In / Sign Up the User via Google One Tap Sign In
     */
    suspend fun signInWithGoogle(call: ApplicationCall)

    /**
     * Used to change information about the User Account
     */
    suspend fun changeData(call: ApplicationCall)

}