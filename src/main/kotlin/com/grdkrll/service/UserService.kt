package com.grdkrll.service

import com.grdkrll.model.dto.user.request.UserChangeDataRequest
import com.grdkrll.model.dto.user.request.UserSignInRequest
import com.grdkrll.model.dto.user.request.UserSignUpRequest
import com.grdkrll.model.dto.user.response.UserResponse
import com.grdkrll.model.dto.user.response.UserSignResponse

interface UserService {
    fun signUp(request: UserSignUpRequest) : UserSignResponse
    fun signIn(request: UserSignInRequest) : UserSignResponse
    fun getByHandle(handle: String) : UserResponse
    fun deleteByHandle(handle: String, id: Int) : UserResponse
    fun signInWithGoogle(googleIdToken: String): UserSignResponse
    fun changeUserData(data: UserChangeDataRequest): UserSignResponse
}