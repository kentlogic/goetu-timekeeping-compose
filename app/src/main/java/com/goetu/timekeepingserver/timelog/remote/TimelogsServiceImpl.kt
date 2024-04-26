package com.goetu.go3timekeepingserver.timelog.remote

import com.goetu.go3timekeepingserver.data.remote.TimelogsService
import com.goetu.go3timekeepingserver.remote.HttpRoutes
import com.goetu.go3timekeepingserver.timelog.domain.model.ImeiRequest
import com.goetu.go3timekeepingserver.timelog.domain.model.TimelogResponse
import com.goetu.go3timekeepingserver.timelog.remote.domain.model.Timelog
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject


class TimelogsServiceImpl  @Inject constructor (private val client: HttpClient) : TimelogsService {

    override suspend fun getTimelogs(imei: ImeiRequest): List<Timelog> {
        val response: TimelogResponse = client.request(HttpRoutes.TIMELOGS) {
            method = HttpMethod.Post
            url(HttpRoutes.TIMELOGS)
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            setBody(imei)
        }.body()
        return if (response.flag == "success") {
               response.internalMessage
        } else {
            emptyList()
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
