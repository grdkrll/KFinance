package com.grdkrll.model.dto.exception.transaction

import com.grdkrll.model.dto.exception.AbstractApiException
import io.ktor.http.*

class EmptyTransactionIdException: AbstractApiException(
    status = HttpStatusCode.NoContent,
    message = "Money transaction id can not be empty"
) {
}