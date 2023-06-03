package com.grdkrll.model.dto.exception

import io.ktor.http.*

/**
 * An Exception Class thrown whenever Unexpected Behavior happens
 */
class UnexpectedException : AbstractApiException(
    status = HttpStatusCode.InternalServerError,
    message = "Something went wrong"
)