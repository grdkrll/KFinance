package com.grdkrll.model.dto.exception.user

import com.grdkrll.model.dto.exception.AbstractApiException
import io.ktor.http.*

/**
 * An Exception Class thrown whenever a User with entered handle or email already exists
 */
class UserAlreadyExistsException : AbstractApiException(
    status = HttpStatusCode.Conflict,
    message = "User already exists"
)