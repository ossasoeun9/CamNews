package com.example.camnews.utils

import android.content.res.Resources
import java.text.SimpleDateFormat
import java.util.Locale

fun Int.dpToPixels() = (this * Resources.getSystem() .displayMetrics .density) . toInt ()

fun String.getDateInAnotherFormat(inputFormat: String,outputFormat: String):String = SimpleDateFormat(inputFormat, Locale.getDefault()).parse(this)?.let { SimpleDateFormat(outputFormat,Locale.getDefault()).format(it) }?:""