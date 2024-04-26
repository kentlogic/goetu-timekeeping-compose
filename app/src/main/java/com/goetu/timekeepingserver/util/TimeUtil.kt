package com.goetu.go3timekeepingserver.util

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TimeUtil {
    fun TimeFromDateTime(dateTime:String) :String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val timeFormat = DateTimeFormatter.ofPattern("hh:mm a" )
            LocalDateTime.parse(dateTime,timeFormat).toString()
        } else {
            val dateFormat =
                SimpleDateFormat("yyyy-mm-dd hh:mm:ss")
            val timeFormat =
                SimpleDateFormat("hh:mm a")

            val dateTimeParsed = dateFormat.parse(dateTime)
            timeFormat.format(dateTimeParsed).toString()
        }



    }
}