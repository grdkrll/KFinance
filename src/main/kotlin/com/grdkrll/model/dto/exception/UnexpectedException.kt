package com.grdkrll.model.dto.exception

import io.ktor.http.*

class UnexpectedException : AbstractApiException(
    status = HttpStatusCode.InternalServerError,
    message = "Something went wrong"
)