package com.grdkrll.model.dto.exception.user

import com.grdkrll.model.dto.exception.AbstractApiException
import io.ktor.http.*

class EmptyHandleException : AbstractApiException(
    status = HttpStatusCode.NoContent,
    message = "User handle can not be empty"
)