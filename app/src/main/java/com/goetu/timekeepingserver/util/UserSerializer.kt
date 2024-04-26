package com.goetu.go3timekeepingserver.util

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.datastore.core.Serializer
import com.goetu.go3timekeepingserver.timelog.remote.domain.model.User
import com.goetu.go3timekeepingserver.util.dataStore.CryptoManager
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

@RequiresApi(Build.VERSION_CODES.M)
class UserSerializer(
    private val cryptoManager: CryptoManager
) : Serializer<User> {

    override val defaultValue: User
        get() = User()

    override suspend fun readFrom(input: InputStream): User {
        val decryptedBytes = cryptoManager.decrypt(input)
        return try {
            Json.decodeFromString(
                deserializer = User.serializer(),
                string = decryptedBytes.decodeToString()
            )
        } catch(e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: User, output: OutputStream) {
        cryptoManager.encrypt(
            bytes = Json.encodeToString(
                serializer = User.serializer(),
                value = t
            ).encodeToByteArray(),
            outputStream = output
        )
    }
}