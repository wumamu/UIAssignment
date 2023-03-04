package com.example.assignment.data.network.responses

import com.example.assignment.data.network.model.RecordDto
import com.google.gson.annotations.SerializedName

class RecordGetResponse(

    @SerializedName("total")
    var total: String,

    @SerializedName("records")
    var records: List<RecordDto>
)