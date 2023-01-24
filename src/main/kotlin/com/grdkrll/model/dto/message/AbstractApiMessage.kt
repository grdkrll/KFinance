package com.grdkrll.model.dto.message

import com.fasterxml.jackson.annotation.JsonIgnore
import com.grdkrll.model.dto.ApiResponse
import io.ktor.http.*

abstract class AbstractApiMessage (
    @JsonIgnore
    override val status: HttpStatusCode = HttpStatusCode.OK,
    override val message: String
) : ApiResponse