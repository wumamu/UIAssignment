package com.example.assignment.data.network.model

import com.google.gson.annotations.SerializedName

data class RecordDto(

    @SerializedName(value = "sitename")
    var siteName: String? = null,

    @SerializedName(value = "county")
    var county: String? = null,

    @SerializedName(value = "aqi")
    var aqi: String? = null,

    @SerializedName(value = "pollutant")
    var pollutant: String? = null,

    @SerializedName(value = "status")
    var status: String? = null,

    @SerializedName(value = "so2")
    var so2: String? = null,

    @SerializedName(value = "co")
    var co: String? = null,

    @SerializedName(value = "o3")
    var o3: String? = null,

    @SerializedName(value = "o3_8hr")
    var o3_8hr: String? = null,

    @SerializedName(value = "pm10")
    var pm10: String? = null,

    @SerializedName(value = "pm2.5")
    var pm2_5: String? = null,

    @SerializedName(value = "no2")
    var no2: String? = null,

    @SerializedName(value = "nox")
    var nox: String? = null,

    @SerializedName(value = "wind_speed")
    var windSpeed: String? = null,

    @SerializedName(value = "wind_direc")
    var windDirect: String? = null,

    @SerializedName(value = "publishtime")
    var publishTime: String? = null,

    @SerializedName(value = "co_8hr")
    var co_8hr: String? = null,

    @SerializedName(value = "pm2.5_avg")
    var pm2_5_avg: String? = null,

    @SerializedName(value = "pm10_avg")
    var pm10_avg: String? = null,

    @SerializedName(value = "so2_avg")
    var so2_avg: String? = null,

    @SerializedName(value = "longitude")
    var longitude: String? = null,

    @SerializedName(value = "latitude")
    var latitude: String? = null,

    @SerializedName(value = "siteid")
    var siteId: String? = null,
)