package com.grdkrll.service

import com.grdkrll.model.dto.user.request.UserChangeDataRequest
import com.grdkrll.model.dto.user.request.UserSignInRequest
import com.grdkrll.model.dto.user.request.UserSignUpRequest
import com.grdkrll.model.dto.user.response.UserResponse
import com.grdkrll.model.dto.user.response.UserSignResponse
import com.grdkrll.util.UserSession

/**
 * A Service Class used for processing API calls about Users
 */
interface UserService {
    /**
     * Used to Sign Up the User and add them to the Database
     */
    fun signUp(request: UserSignUpRequest) : UserSignResponse

    /**
     * Used to Sign In the User
     */
    fun signIn(request: UserSignInRequest) : UserSignResponse

    /**
     * Used to get basic information about a User by their handle
     */
    fun getByHandle(handle: String) : UserResponse

    /**
     * Used to delete a User Account by their handle
     *
     * @param handle the Handle of the User to delete
     * @param id the id the of the User Session
     */
    fun deleteByHandle(handle: String, id: Int) : UserResponse

    /**
     * Used to Sign In / Sign Up the User via Google One Tap Auth
     *
     * @param googleIdToken the Google Token of the User
     */
    fun signInWithGoogle(googleIdToken: String): UserSignResponse

    /**
     * Used to change information of the User Account
     *
     * @param session the User Session from the JWT Token
     * @param data the Request data to change the User Account
     */
    fun changeUserData(session: UserSession, data: UserChangeDataRequest): UserSignResponse
}