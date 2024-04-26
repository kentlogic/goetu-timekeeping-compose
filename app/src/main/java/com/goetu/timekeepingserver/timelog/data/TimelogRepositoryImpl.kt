package com.goetu.go3timekeepingserver.timelog.data

import android.util.Log
import coil.network.HttpException
import com.goetu.go3timekeepingserver.timelog.domain.model.ImeiRequest
import com.goetu.go3timekeepingserver.timelog.domain.repository.TimelogRepository
import com.goetu.go3timekeepingserver.timelog.remote.TimelogsServiceImpl
import com.goetu.go3timekeepingserver.timelog.remote.domain.model.Timelog
import com.goetu.go3timekeepingserver.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TimelogRepositoryImpl @Inject constructor(
    private val api: TimelogsServiceImpl,
    private val getImei : String
) : TimelogRepository {

    override suspend fun getTimelogs(): Flow<Resource<List<Timelog>>> {
        return flow {
            emit(Resource.Loading(true))
            val imei = getImei
            //358240051111110
           // val imei =  "GOETU-QA-IMEI-002"
             val timelogs = try {
                api.getTimelogs(ImeiRequest(imei = imei))

            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Unable to load time logs"))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Unable to load time logs"))
            }

            emit(Resource.Loading(false))

            timelogs.let { timelogs ->
                 emit(Resource.Success(timelogs as List<Timelog>))
            }
        }
    }
}