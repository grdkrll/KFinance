package com.grdkrll.model.dto.user.request

/**
 * A Request Class used to Sign In / Sign Up the User via Google One Tap Auth
 *
 * @property googleIdToken a Token of a Google Account of the User
 */
class GoogleSignInRequest(
    val googleIdToken: String
)