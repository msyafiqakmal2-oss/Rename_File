package org.example

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    println("Server Ktor sedang dinyalakan...")
    embeddedServer(Netty, port = 8080) {
        routing {
            get("/") {
                call.respondText("Halo Syafiq! Server Kotlin berhasil jalan 🚀")
            }
        }
    }.start(wait = true)
}