package com.grdkrll.model.dto.shared

import com.grdkrll.model.dto.message.AbstractApiMessage

/**
 * A Response Class used for checking that API is working
 */
class HealthyMessage : AbstractApiMessage(message = "API is working!")