package org.example

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.request.*
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

// DTO (Data Transfer Object) khusus untuk request body saat membuat user baru
@Serializable
data class CreateUserRequest(
    val nama: String,
    val role: String
)

// Data in-memory sementara
val usersList = mutableListOf(
    User(id = 1, nama = "Syafiq Akmal", role = "Backend Developer"),
    User(id = 2, nama = "Budi Santoso", role = "Frontend Developer"),
    User(id = 3, nama = "Siti Rahma", role = "UI/UX Designer")
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

            // GET All Users
            get("/api/users") {
                call.respond(usersList)
            }

            // POST Create User
            post("/api/users") {
                try {
                    // Menerima & men-deserialize JSON dari request body
                    val request = call.receive<CreateUserRequest>()
                    
                    // Auto-increment ID sederhana
                    val newId = (usersList.maxOfOrNull { it.id } ?: 0) + 1
                    val newUser = User(id = newId, nama = request.nama, role = request.role)
                    
                    usersList.add(newUser)
                    
                    // Kirim respon 201 Created beserta data user yang baru dibuat
                    call.respond(HttpStatusCode.Created, newUser)
                } catch (e: Exception) {
                    call.respond(
                        HttpStatusCode.BadRequest, 
                        mapOf("error" to "Format JSON tidak valid atau data tidak lengkap")
                    )
                }
            }
        }
    }.start(wait = true)
}