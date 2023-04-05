package ndy.ktor.context.auth

import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.call
import io.ktor.server.auth.Principal
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.authentication
import io.ktor.server.resources.delete
import io.ktor.server.resources.get
import io.ktor.server.resources.head
import io.ktor.server.resources.options
import io.ktor.server.resources.post
import io.ktor.server.resources.put
import io.ktor.server.routing.Route
import io.ktor.server.routing.patch
import io.ktor.util.pipeline.PipelineContext

suspend inline fun <reified T : Any> PipelineContext<Unit, ApplicationCall>.run(
    crossinline build: suspend context(AuthenticationContext<Principal>, ApplicationCallContext) (T) -> Unit,
    call: ApplicationCall,
    it: T
): Unit = build(authenticationContext(call.authentication), applicationCallContext(call), it)


/**
 * combines `authenticate` & `get<T>`
 * A class [T] **must** be annotated with [io.ktor.resources.Resource].
 */
inline fun <reified T : Any> Route.authenticatedGet(
    vararg configurations: String? = arrayOf(null),
    optional: Boolean = false,
    crossinline build: suspend context(AuthenticationContext<Principal>, ApplicationCallContext) (T) -> Unit
) = authenticate(configurations = configurations, optional = optional) {
    get<T> {
        this.run(build, call, it)
    }
}

/**
 * combines `authenticate` & `options<T>`
 * A class [T] **must** be annotated with [io.ktor.resources.Resource].
 */
inline fun <reified T : Any> Route.authenticatedOptions(
    vararg configurations: String? = arrayOf(null),
    optional: Boolean = false,
    crossinline build: suspend context(AuthenticationContext<Principal>, ApplicationCallContext) (T) -> Unit
) = authenticate(configurations = configurations, optional = optional) {
    options<T> {
        this.run(build, call, it)
    }
}

/**
 * combines `authenticate` & `head<T>`
 * A class [T] **must** be annotated with [io.ktor.resources.Resource].
 */
inline fun <reified T : Any> Route.authenticatedHead(
    vararg configurations: String? = arrayOf(null),
    optional: Boolean = false,
    crossinline build: suspend context(AuthenticationContext<Principal>, ApplicationCallContext) (T) -> Unit
) = authenticate(configurations = configurations, optional = optional) {
    head<T> {
        this.run(build, call, it)
    }
}

/**
 * combines `authenticate` & `post<T>`
 * A class [T] **must** be annotated with [io.ktor.resources.Resource].
 */
inline fun <reified T : Any> Route.authenticatedPost(
    vararg configurations: String? = arrayOf(null),
    optional: Boolean = false,
    crossinline build: suspend context(AuthenticationContext<Principal>, ApplicationCallContext) (T) -> Unit
) = authenticate(configurations = configurations, optional = optional) {
    post<T> {
        this.run(build, call, it)
    }
}

/**
 * combines `authenticate` & `put<T>`
 * A class [T] **must** be annotated with [io.ktor.resources.Resource].
 */
inline fun <reified T : Any> Route.authenticatedPut(
    vararg configurations: String? = arrayOf(null),
    optional: Boolean = false,
    crossinline build: suspend context(AuthenticationContext<Principal>, ApplicationCallContext) (T) -> Unit
) = authenticate(configurations = configurations, optional = optional) {
    put<T> {
        this.run(build, call, it)
    }
}

/**
 * combines `authenticate` & `delete<T>`
 * A class [T] **must** be annotated with [io.ktor.resources.Resource].
 */
inline fun <reified T : Any> Route.authenticatedDelete(
    vararg configurations: String? = arrayOf(null),
    optional: Boolean = false,
    crossinline build: suspend context(AuthenticationContext<Principal>, ApplicationCallContext) (T) -> Unit
) = authenticate(configurations = configurations, optional = optional) {
    delete<T> {
        this.run(build, call, it)
    }
}

/**
 * combines `authenticate` & `patch<T>`
 * A class [T] **must** be annotated with [io.ktor.resources.Resource].
 */
inline fun <reified T : Any> Route.authenticatedPatch(
    vararg configurations: String? = arrayOf(null),
    optional: Boolean = false,
    crossinline build: suspend context(AuthenticationContext<Principal>, ApplicationCallContext) (T) -> Unit
) = authenticate(configurations = configurations, optional = optional) {
    patch<T> {
        this.run(build, call, it)
    }
}
