package com.grdkrll.model.dto.exception.user

import com.grdkrll.model.dto.exception.AbstractApiException
import io.ktor.http.*

/**
 * An Exception Class thrown whenever Hash of the entered Password doesn't match with Hash of the stored Password
 */
class PasswordNotMatchException : AbstractApiException(
    status = HttpStatusCode.Unauthorized,
    message = "Passwords don't match"
)