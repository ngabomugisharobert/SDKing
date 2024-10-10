package com.nmrobert.mysdk


import android.content.Context
import android.util.Log
import org.json.JSONObject
import java.io.File
import java.io.FileWriter
import java.io.IOException

class FileLogger(context: Context) {

    private val logFile: File
    private var fileWriter: FileWriter? = null
    private val TAG = "FileLogger"

    init {
        // Create or get the log file in the app's internal storage
        val logDir = context.filesDir
        logFile = File(logDir, "analytics_events.log")

        try {
            fileWriter = FileWriter(logFile, true) // Append mode
            Log.d(TAG, "Log file created/initialized at: ${logFile.absolutePath}")
        } catch (e: IOException) {
            Log.e(TAG, "Error initializing log file", e)
        }
    }

    // Log event data as JSON into the file
    fun logEvent(eventData: Map<String, Any>) {
        try {
            val json = JSONObject(eventData).toString()
            fileWriter?.apply {
                write("$json\n")  // Write JSON event to the file
                flush()           // Make sure it’s saved to disk
            }
            Log.d(TAG, "Event logged: $json")
        } catch (e: IOException) {
            Log.e(TAG, "Error logging event", e)
        }
    }

    // Close the file writer when done
    fun closeLogger() {
        try {
            fileWriter?.close()
        } catch (e: IOException) {
            Log.e(TAG, "Error closing log file", e)
        }
    }
}