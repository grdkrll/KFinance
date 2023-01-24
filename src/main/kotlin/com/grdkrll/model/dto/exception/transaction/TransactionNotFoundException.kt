package com.grdkrll.model.dto.exception.transaction

import com.grdkrll.model.dto.exception.AbstractApiException
import io.ktor.http.*

class TransactionNotFoundException: AbstractApiException(
    status = HttpStatusCode.NotFound,
    message = "Transaction is not found"
)