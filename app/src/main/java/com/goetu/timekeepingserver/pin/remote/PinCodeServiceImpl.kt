package com.goetu.go3timekeepingserver.timelog.remote

import com.goetu.go3timekeepingserver.data.remote.PinCodeService
import com.goetu.go3timekeepingserver.remote.HttpRoutes
import com.goetu.go3timekeepingserver.timelog.domain.model.ImeiRequest
import com.goetu.go3timekeepingserver.timelog.domain.model.PinCodeResponse
import com.goetu.go3timekeepingserver.timelog.remote.domain.model.PinCode
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject


class PinCodeServiceImpl  @Inject constructor (private val client: HttpClient) : PinCodeService {

      override suspend fun getPinCode(imei: ImeiRequest): PinCode {
        val response: PinCodeResponse = client.request(HttpRoutes.TIMELOGS) {
            method = HttpMethod.Post
            url(HttpRoutes.PIN_CODE)
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            setBody(imei)
        }.body()
        return if (response.flag == "success") {
            print("asas ${response.flag} ${response.internalMessage}")
              response.internalMessage
        } else {
            PinCode(1234)
        }

    }
}

//
//        catch (e: RedirectResponseException) {
//            // 3xx - response
//            print("Error: ${e.response.status.description}")
//            emptyList()
//        }
//        catch (e: ClientRequestException) {
//            // 4xx - response
//            print("Error: ${e.response.status.description}")
//            emptyList()
//        }catch (e: ServerResponseException) {
//            // 5xx - response
//            print("Error: ${e.response.status.description}")
//            emptyList()
//        } catch (e: Exception) {
//            print("Error: ${e.message}")
//            emptyList()
//        }
