package com.wahyudwi.notesapp.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    fun getCurrentData(): String {
        val dateFormat = SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault())
        val date = Date()
        return dateFormat.format(date)
    }
}