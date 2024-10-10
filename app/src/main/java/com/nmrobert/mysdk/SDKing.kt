package com.nmrobert.mysdk

import android.content.Context
import android.util.Log


object AnalyticsSDK {

    private lateinit var fileLogger: FileLogger
    private const val TAG = "SDKing"

    // Initialize the SDK with a context
    fun initialize(context: Context) {
        // Initialize the file logger, where all events will be logged
        fileLogger = FileLogger(context)
        Log.d(TAG, "Analytics SDK initialized")
    }

    // Track an event and save it to the log file
    fun trackEvent(eventName: String, properties: Map<String, Any>? = null) {
        val eventDetails = mutableMapOf<String, Any>()
        eventDetails["eventName"] = eventName
        eventDetails["timestamp"] = System.currentTimeMillis()
        properties?.let { eventDetails.putAll(it) }

        // Log the event to the file
        fileLogger.logEvent(eventDetails)
    }

    // Flush data (if needed) or do any final cleanup
    fun shutdown() {
        fileLogger.closeLogger()
        Log.d(TAG, "Analytics SDK shutdown")
    }
}