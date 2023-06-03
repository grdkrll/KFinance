package com.grdkrll.model.dto.exception.group

import com.grdkrll.model.dto.exception.AbstractApiException
import io.ktor.http.*

/**
 * An Exception Class thrown whenever requested Group was Not Found
 */
class GroupNotFoundException : AbstractApiException(
    status = HttpStatusCode.NotFound,
    message = "Group is not found"
)