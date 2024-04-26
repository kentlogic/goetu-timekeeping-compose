//package com.goetu.timekeepingserver.util
//
//import android.content.Context
//import android.telephony.TelephonyManager
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat.getSystemService
//import com.goetu.go3timekeepingserver.Manifest
//
//object DeviceImei {
//
//    init {
//
//    }
//
//    val telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as
//            TelephonyManager
//    if (ActivityCompat.checkSelfPermission(this@MainActivity,
//    Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//        ActivityCompat.requestPermissions(this@MainActivity,
//            arrayOf(Manifest.permission.READ_PHONE_STATE), REQUEST_CODE)
//        return@setOnClickListener
//    }
//    IMEINumber = telephonyManager.deviceId
//
//}