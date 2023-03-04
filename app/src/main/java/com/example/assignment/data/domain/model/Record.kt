package com.example.assignment.data.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

// Domain Model
@Parcelize
data class Record(
    val siteName: String? = null,
    val county: String? = null,
    val aqi: String? = null,
    val pollutant: String? = null,
    val status: String? = null,
    val so2: String? = null,
    val co: String? = null,
    val o3: String? = null,
    val o3_8hr: String? = null,
    val pm10: String? = null,
    val pm2_5: Int? = null,
    val no2: String? = null,
    val nox: String? = null,
    val windSpeed: String? = null,
    val windDirect: String? = null,
    val publishTime: String? = null,
    val co_8hr: String? = null,
    val pm2_5_Avg: String? = null,
    val pm10_Avg: String? = null,
    val so2_Avg: String? = null,
    val longitude: String? = null,
    val latitude: String? = null,
    val sitedId: Int? = null,
) : Parcelable
