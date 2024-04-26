package com.goetu.go3timekeepingserver.data.remote

import com.goetu.go3timekeepingserver.timelog.domain.model.ImeiRequest
import com.goetu.go3timekeepingserver.timelog.remote.PinCodeServiceImpl
import com.goetu.go3timekeepingserver.timelog.remote.domain.model.PinCode
 import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*

import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

interface PinCodeService {

   // suspend fun getPosts(): List<PostResponse>
    suspend fun getPinCode(imei: ImeiRequest): PinCode

    //suspend fun createPost(postRequest: PostRequest): PostResponse?

    companion object {
        fun create(): PinCodeService {
            return PinCodeServiceImpl(
                client = HttpClient(Android ) {
                    install(Logging) {
                        level = LogLevel.ALL
                        logger = Logger.ANDROID
                     }
                    install(ContentNegotiation) {
                        json(Json {
                            prettyPrint = true
                            isLenient = true
                            encodeDefaults = true
                            ignoreUnknownKeys = true
                        })
                    }
                    install(HttpRedirect) {
                        checkHttpMethod = false
                        allowHttpsDowngrade = true
                    }
                    install(Auth) {
                    bearer {  loadTokens {
                        // Load tokens from a local storage and return them as the 'BearerTokens' instance
                        BearerTokens("BNKL03L1IOH6N9OI4QO2I6XNKX2YGIS7", "BNKL03L1IOH6N9OI4QO2I6XNKX2YGIS7")
                    } }
                    }

                }
            )
        }
    }
}