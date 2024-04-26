package com.goetu.go3timekeepingserver.timelog.remote

 import android.util.Log
 import com.goetu.go3timekeepingserver.remote.HttpRoutes
 import com.goetu.go3timekeepingserver.timelog.domain.model.LoginResponse
 import com.goetu.go3timekeepingserver.timelog.remote.domain.model.LoginRequest
 import io.ktor.client.*
 import io.ktor.client.call.*
 import io.ktor.client.request.*
 import io.ktor.http.*
 import javax.inject.Inject


class AuthServiceImpl  @Inject constructor (private val client: HttpClient) {

    suspend fun login(loginRequest: LoginRequest): LoginResponse {
        val response: LoginResponse = client.request(HttpRoutes.LOGIN) {
            method = HttpMethod.Post
            url(HttpRoutes.LOGIN)
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            setBody(loginRequest)
        }.body()


        return response

    }
}
