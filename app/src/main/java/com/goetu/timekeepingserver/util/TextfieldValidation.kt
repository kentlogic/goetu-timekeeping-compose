package com.goetu.go3timekeepingserver.util



fun isValidEmail(email: String): Boolean {
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
         return true
    }

    return false
}

fun isFieldNotEmpty(fieldText: String): Boolean {
    if (fieldText.isNotEmpty()) {
        return true
    }
    return false
}
