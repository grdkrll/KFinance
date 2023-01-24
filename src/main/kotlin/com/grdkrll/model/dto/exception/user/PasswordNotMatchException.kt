package com.grdkrll.model.dto.exception.user

import com.grdkrll.model.dto.exception.AbstractApiException
import io.ktor.http.*

class PasswordNotMatchException : AbstractApiException(
    status = HttpStatusCode.Unauthorized,
    message = "Passwords don't match"
)