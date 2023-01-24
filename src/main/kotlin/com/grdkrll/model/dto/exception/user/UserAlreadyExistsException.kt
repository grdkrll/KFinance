package com.grdkrll.model.dto.exception.user

import com.grdkrll.model.dto.exception.AbstractApiException
import io.ktor.http.*

class UserAlreadyExistsException : AbstractApiException(
    status = HttpStatusCode.Conflict,
    message = "User already exists"
)