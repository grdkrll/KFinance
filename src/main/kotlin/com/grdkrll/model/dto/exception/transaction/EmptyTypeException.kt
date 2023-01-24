package com.grdkrll.model.dto.exception.transaction

import com.grdkrll.model.dto.exception.AbstractApiException
import io.ktor.http.*

class EmptyTypeException : AbstractApiException(
    status = HttpStatusCode.NoContent,
    message = "Type of transaction can not be empty"
)