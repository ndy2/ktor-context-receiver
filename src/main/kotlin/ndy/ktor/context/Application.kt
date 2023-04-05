package ndy.ktor.context

import io.ktor.resources.Resource
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.UserIdPrincipal
import io.ktor.server.auth.basic
import io.ktor.server.auth.principal
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.resources.Resources
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import ndy.ktor.context.auth.authenticatedGet

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(Resources)
    install(Authentication) {
        basic { validate { UserIdPrincipal("basic auth with default - ${it.name}") } } // always authenticated
        basic("basic") { validate { UserIdPrincipal("basic auth with name - ${it.name}") } }  // always authenticated
    }


    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        // ktor-resource, with default auth
        authenticatedGet<Articles> {
            call.respondText("name : ${call.principal<UserIdPrincipal>()!!.name}")
        }

        // ktor-resource, with default auth
        authenticatedGet<Profiles>("basic") {
            call.respondText("name : ${call.principal<UserIdPrincipal>()!!.name}")
        }
    }
}

@Resource("/articles")
class Articles

@Resource("/profiles")
class Profiles
