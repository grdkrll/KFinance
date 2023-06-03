package com.grdkrll.model.dto.exception.user

import com.grdkrll.model.dto.exception.AbstractApiException
import io.ktor.http.*

/**
 * An Exception Class thrown whenever requested User has an empty handle
 */
class EmptyHandleException : AbstractApiException(
    status = HttpStatusCode.NoContent,
    message = "User handle can not be empty"
)