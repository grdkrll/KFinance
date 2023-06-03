package com.grdkrll.model.dto.exception.user

import com.grdkrll.model.dto.exception.AbstractApiException
import io.ktor.http.*

/**
 * An Exception Class throw whenever requested User was Not Found
 */
class UserNotFoundException : AbstractApiException(
    status = HttpStatusCode.NotFound,
    message = "User is not found"
)