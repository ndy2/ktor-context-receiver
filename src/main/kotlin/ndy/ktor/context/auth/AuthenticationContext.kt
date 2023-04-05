package ndy.ktor.context.auth

import io.ktor.server.auth.Principal

typealias KtorAuthenticationContext = io.ktor.server.auth.AuthenticationContext

/**
 * context of authenticated user
 *
 * - ref - https://youtu.be/NxDIq-rFXUM
 */
interface AuthenticationContext<T : Principal> {

    val authenticated: Boolean

    val principal: T
}

inline fun <reified T : Principal> authenticationContext(authentication: KtorAuthenticationContext) =
    object : AuthenticationContext<T> {

        // authenticated if ktor authentication context has principal
        override val authenticated = (authentication.principal<T>()) != null

        // refer principal with no principal is not allowed
        override val principal by lazy { (authentication.principal<T>()) ?: error("illegal principal access") }
    }
