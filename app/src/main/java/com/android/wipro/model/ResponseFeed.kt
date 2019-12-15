package com.android.wipro.model


import com.fasterxml.jackson.annotation.JsonProperty

data class ResponseFeed(
    @JsonProperty("rows")
    var rows: List<Row>,
    @JsonProperty("title")
    var title: String?
)