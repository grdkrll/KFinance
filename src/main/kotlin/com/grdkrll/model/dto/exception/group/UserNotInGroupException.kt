package com.grdkrll.model.dto.exception.group

import com.grdkrll.model.dto.exception.AbstractApiException
import io.ktor.http.*

/**
 * An Exception Class thrown whenever requested User is not a Member of the requested Group
 */
class UserNotInGroupException : AbstractApiException(
    status = HttpStatusCode.NotFound,
    message = "User is not in this group"
)