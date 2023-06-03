package com.grdkrll.model.dto.exception

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.grdkrll.model.dto.ApiResponse
import io.ktor.http.*

/**
 * An Abstract Class Used for API Exceptions
 */
@JsonIgnoreProperties("cause", "stackTrace", "suppressed", "localizedMessage")
abstract class AbstractApiException(
    @JsonIgnore
    override val status: HttpStatusCode,
    override val message: String
) : ApiResponse, Exception()