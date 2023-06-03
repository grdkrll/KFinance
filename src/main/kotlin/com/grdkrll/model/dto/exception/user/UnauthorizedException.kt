package com.grdkrll.model.dto.exception.user

import com.grdkrll.model.dto.exception.AbstractApiException
import io.ktor.http.*

/**
 * An Exception Class thrown whenever the API call doesn't have a correct JWT token of a User
 */
class UnauthorizedException : AbstractApiException(
    status = HttpStatusCode.Unauthorized,
    message = "Unauthorized"
)
