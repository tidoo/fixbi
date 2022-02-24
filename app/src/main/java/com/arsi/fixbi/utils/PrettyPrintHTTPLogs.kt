package com.arsi.fixbi.utils

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import okhttp3.logging.HttpLoggingInterceptor


class PrettyPrintHTTPLogs : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        val logName = "OkHttp"
        if (!message.startsWith("{")) {
            Log.d(logName, message)
            return
        }
        try {
            val prettyPrintJson = GsonBuilder().setPrettyPrinting().create().toJson(JsonParser().parse(message))
            Log.d(logName, prettyPrintJson)


//            var prettyJson = ""
//            prettyPrintJson.lines().forEach { prettyJson += it }
//            Log.d(logName, prettyJson)
        } catch (m: JsonSyntaxException) {
            Log.d(logName, message)
        }
    }
}