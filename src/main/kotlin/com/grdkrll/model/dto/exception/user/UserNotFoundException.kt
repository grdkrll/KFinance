package com.grdkrll.model.dto.exception.user

import com.grdkrll.model.dto.exception.AbstractApiException
import io.ktor.http.*

class UserNotFoundException : AbstractApiException(
    status = HttpStatusCode.NotFound,
    message = "User is not found"
)