package com.grdkrll.model.dto.exception.group

import com.grdkrll.model.dto.exception.AbstractApiException
import io.ktor.http.*

class UserNotInGroupException : AbstractApiException(
    status = HttpStatusCode.NotFound,
    message = "User is not in this group"
)