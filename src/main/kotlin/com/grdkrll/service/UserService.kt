package com.grdkrll.service

import com.grdkrll.model.dto.user.request.UserSignInRequest
import com.grdkrll.model.dto.user.request.UserSignUpRequest
import com.grdkrll.model.dto.user.response.TokenResponse
import com.grdkrll.model.dto.user.response.UserResponse

interface UserService {
    fun signUp(request: UserSignUpRequest) : UserResponse
    fun signIn(request: UserSignInRequest) : TokenResponse
    fun getByHandle(handle: String) : UserResponse
    fun deleteByHandle(handle: String, id: Int) : UserResponse
}