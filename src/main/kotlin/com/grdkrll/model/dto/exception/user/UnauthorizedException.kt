package com.grdkrll.model.dto.exception.user

import com.grdkrll.model.dto.exception.AbstractApiException
import io.ktor.http.*

class UnauthorizedException : AbstractApiException(
    status = HttpStatusCode.Unauthorized,
    message = "Unauthorized"
)
