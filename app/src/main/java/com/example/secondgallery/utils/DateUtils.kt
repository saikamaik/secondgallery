package com.example.secondgallery.utils

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    @JvmStatic
    public fun checkDateFormat(date: String): String {
        var sdf = SimpleDateFormat("-yyyy-mm-dd'T'HH:MM:ss", Locale.getDefault())

        try {
            return convertDateFormat(date, sdf)
        } catch (e: ParseException) {
            sdf = SimpleDateFormat("yyyy-mm-dd'T'HH:MM:ss", Locale.getDefault())
        }

        return convertDateFormat(date, sdf)
    }

    private fun convertDateFormat(date: String, sdf: SimpleDateFormat): String {

        val output = SimpleDateFormat("dd-mm-yyyy", Locale.getDefault())
        val d = sdf.parse(date)
        return output.format(d)

    }

    @SuppressLint("SimpleDateFormat")
    @JvmStatic
    public fun convertFromStringToDate(date: String?) : Date? {
        if (date!=null) return null
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        return format.parse(date)
    }
}