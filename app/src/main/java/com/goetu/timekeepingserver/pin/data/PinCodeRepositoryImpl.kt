package com.goetu.go3timekeepingserver.timelog.data

import android.util.Log
import coil.network.HttpException
import com.goetu.go3timekeepingserver.timelog.domain.model.ImeiRequest
import com.goetu.go3timekeepingserver.timelog.domain.repository.PinCodeRepository
import com.goetu.go3timekeepingserver.timelog.remote.PinCodeServiceImpl
import com.goetu.go3timekeepingserver.timelog.remote.domain.model.PinCode
import com.goetu.go3timekeepingserver.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PinCodeRepositoryImpl @Inject constructor(
    private val api: PinCodeServiceImpl,
    private val getImei: String
) : PinCodeRepository {

    override suspend fun getPinCode(): Flow<Resource<PinCode>> {
        return flow {
            emit(Resource.Loading(true))
            val imei = getImei
            //358240051111110
           // val imei =  "GOETU-QA-IMEI-002"
            val pincode = try {
                api.getPinCode(ImeiRequest(imei = imei))

            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Unable to load time logs"))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Unable to load time logs"))
            }

            emit(Resource.Loading(false))

            pincode.let { pincode ->
                  emit(Resource.Success(pincode as PinCode))
            }
        }
    }
}