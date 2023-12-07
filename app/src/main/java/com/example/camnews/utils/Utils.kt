package com.example.camnews.utils

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


object Utils {
    fun stringToDateFormatted(
        dateAsString: String,
        inputPattern: String = "yyyy-MM-dd'T'HH:mm:ss",
        outputPattern: String = "dd MMM, yyyy"
    ): String {
        val locale =  Locale.getDefault()

        val inputFormatter = SimpleDateFormat(inputPattern, locale)
        val date = inputFormatter.parse(dateAsString) ?: return ""

        val outPutFormatter = SimpleDateFormat(outputPattern, locale)
        return outPutFormatter.format(date)
    }
}