package com.example.secondgallery.utils

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    @JvmStatic
    fun checkDateFormat(date: String): String {
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
    fun convertFromStringToDate(date: String): String {
        val parser = SimpleDateFormat("dd-mm-yyyy")
        val formatter = SimpleDateFormat("yyyy-mm-dd'T'HH:MM:ss")
        return formatter.format(parser.parse(date))
    }
}