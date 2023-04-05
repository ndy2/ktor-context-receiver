package ndy.ktor.auth

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.server.testing.*
import kotlin.test.*
import io.ktor.http.*
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.resources.Resources
import io.ktor.server.resources.get
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            install(Resources)
            routing {
                get("/") {
                    call.respondText("Hello World!")
                }
                get<Articles> { article ->
                    // Get all articles ...
                    call.respond("List of articles sorted starting from ${article.sort}")
                }
            }
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Hello World!", bodyAsText())
        }
    }
}
