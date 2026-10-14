package org.example
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.html.*
import io.ktor.server.routing.*
import kotlinx.html.*

fun main() {
    println("Menyalakan server Ktor dengan UI Modern...")
    embeddedServer(Netty, port = 8080) {
        routing {
            get("/") {
                call.respondHtml {
                    head {
                        title { +"Kotlin Ktor Dashboard" }
                        // Import Tailwind CSS biar tampilan langsung cakep
                        script { src = "https://cdn.tailwindcss.com" }
                    }
                    body {
                        classes = setOf("bg-slate-900", "text-white", "min-h-screen", "flex", "items-center", "justify-center", "p-6")
                        
                        div(classes = "max-w-md w-full bg-slate-800 rounded-2xl shadow-xl p-8 border border-slate-700 text-center") {
                            div(classes = "inline-flex items-center justify-center w-16 h-16 bg-indigo-500/10 text-indigo-400 rounded-full mb-4") {
                                +"🚀"
                            }
                            h1(classes = "text-2xl font-bold mb-2 text-white") {
                                +"Ktor Web Server"
                            }
                            p(classes = "text-slate-400 text-sm mb-6") {
                                +"Server Kotlin kamu berhasil di-upgrade dengan tampilan UI modern & Tailwind CSS."
                            }
                            
                            div(classes = "bg-slate-900/60 rounded-xl p-4 text-left border border-slate-700/50 mb-6") {
                                div(classes = "flex justify-between items-center mb-2") {
                                    span(classes = "text-xs font-semibold text-indigo-400 uppercase tracking-wider") { +"Status Server" }
                                    span(classes = "w-2 h-2 rounded-full bg-emerald-500 animate-pulse") {}
                                }
                                p(classes = "text-sm text-slate-300 font-mono") { +"HTTP 200 OK — Ready" }
                            }

                            a(href = "/api/info", classes = "inline-block w-full py-3 px-4 bg-indigo-600 hover:bg-indigo-500 text-white font-medium text-sm rounded-xl transition duration-200 shadow-lg shadow-indigo-600/30") {
                                +"Cek Endpoint API"
                            }
                        }
                    }
                }
            }

            // Endpoint API sederhana buat tes JSON / Teks
            get("/api/info") {
                call.respondHtml {
                    head { script { src = "https://cdn.tailwindcss.com" } }
                    body(classes = "bg-slate-900 text-white p-8 font-mono") {
                        h2(classes = "text-xl font-bold text-indigo-400 mb-4") { +"[API Response]" }
                        pre(classes = "bg-slate-800 p-4 rounded-xl text-emerald-400 border border-slate-700") {
                            +"""{
  "status": "success",
  "message": "Hello dari Ktor Backend!",
  "framework": "Ktor 2.3.12"
}"""
                        }
                        a(href = "/", classes = "inline-block mt-4 text-slate-400 hover:text-white underline text-sm") {
                            +"← Kembali ke Beranda"
                        }
                    }
                }
            }
        }
    }.start(wait = true)
}