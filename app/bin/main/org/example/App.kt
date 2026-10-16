package org.example

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val nama: String,
    val role: String
)

fun main() {
    println("Menyalakan server Ktor REST API...")
    embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            json()
        }

        routing {
            get("/") {
                call.respondText("Server REST API Ktor Siap!")
            }

            get("/api/profile") {
                val profile = User(id = 1, nama = "Syafiq Akmal", role = "Backend Developer")
                call.respond(profile)
            }

            get("/api/users") {
                val usersList = listOf(
                    User(id = 1, nama = "Syafiq Akmal", role = "Backend Developer"),
                    User(id = 2, nama = "Budi Santoso", role = "Frontend Developer"),
                    User(id = 3, nama = "Siti Rahma", role = "UI/UX Designer")
                )
                call.respond(usersList)
            }
        }
    }.start(wait = true)
}