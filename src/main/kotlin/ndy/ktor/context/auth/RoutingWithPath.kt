package ndy.ktor.context.auth


import io.ktor.server.application.call
import io.ktor.server.auth.Principal
import io.ktor.server.auth.authenticate
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.head
import io.ktor.server.routing.options
import io.ktor.server.routing.patch
import io.ktor.server.routing.post
import io.ktor.server.routing.put


/**
 * combines 'authenticate` & `get`
 */
inline fun Route.authenticatedGet(
    path: String,
    vararg configurations: String? = arrayOf(null),
    optional: Boolean = false,
    crossinline build: suspend context(AuthenticationContext<Principal>, ApplicationCallContext) (Unit) -> Unit
) {
    authenticate(configurations = configurations, optional = optional) {
        get(path) {
            this.run(build, call, it)
        }
    }
}

/**
 * combines 'authenticate` & `options`
 */
inline fun Route.authenticatedOptions(
    path: String,
    vararg configurations: String? = arrayOf(null),
    optional: Boolean = false,
    crossinline build: suspend context(AuthenticationContext<Principal>, ApplicationCallContext) (Unit) -> Unit
) {
    authenticate(configurations = configurations, optional = optional) {
        options(path) {
            this.run(build, call, it)
        }
    }
}

/**
 * combines 'authenticate` & `head`
 */
inline fun Route.authenticatedHead(
    path: String,
    vararg configurations: String? = arrayOf(null),
    optional: Boolean = false,
    crossinline build: suspend context(AuthenticationContext<Principal>, ApplicationCallContext) (Unit) -> Unit
) {
    authenticate(configurations = configurations, optional = optional) {
        head(path) {
            this.run(build, call, it)
        }
    }
}

/**
 * combines 'authenticate` & `post`
 */
inline fun Route.authenticatedPost(
    path: String,
    vararg configurations: String? = arrayOf(null),
    optional: Boolean = false,
    crossinline build: suspend context(AuthenticationContext<Principal>, ApplicationCallContext) (Unit) -> Unit
) {
    authenticate(configurations = configurations, optional = optional) {
        post(path) {
            this.run(build, call, it)
        }
    }
}

/**
 * combines 'authenticate` & `put`
 */
inline fun Route.authenticatedPut(
    path: String,
    vararg configurations: String? = arrayOf(null),
    optional: Boolean = false,
    crossinline build: suspend context(AuthenticationContext<Principal>, ApplicationCallContext) (Unit) -> Unit
) {
    authenticate(configurations = configurations, optional = optional) {
        put(path) {
            this.run(build, call, it)
        }
    }
}

/**
 * combines 'authenticate` & `delete`
 */
inline fun Route.authenticatedDelete(
    path: String,
    vararg configurations: String? = arrayOf(null),
    optional: Boolean = false,
    crossinline build: suspend context(AuthenticationContext<Principal>, ApplicationCallContext) (Unit) -> Unit
) {
    authenticate(configurations = configurations, optional = optional) {
        delete(path) {
            this.run(build, call, it)
        }
    }
}

/**
 * combines 'authenticate` & `patch`
 */
inline fun Route.authenticatedPatch(
    path: String,
    vararg configurations: String? = arrayOf(null),
    optional: Boolean = false,
    crossinline build: suspend context(AuthenticationContext<Principal>, ApplicationCallContext) (Unit) -> Unit
) {
    authenticate(configurations = configurations, optional = optional) {
        patch(path) {
            this.run(build, call, it)
        }
    }
}
