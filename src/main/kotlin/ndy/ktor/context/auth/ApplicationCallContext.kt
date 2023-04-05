package ndy.ktor.context.auth

import io.ktor.server.application.ApplicationCall


/**
 * context of ApplicationCall
 */
interface ApplicationCallContext {
    val call: ApplicationCall
}

fun applicationCallContext(call: ApplicationCall) =
    object : ApplicationCallContext {
        override val call = call
    }
