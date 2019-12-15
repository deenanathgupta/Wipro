package com.android.wipro.model


import com.fasterxml.jackson.annotation.JsonProperty

data class Row(
    @JsonProperty("description")
    var description: String?,
    @JsonProperty("imageHref")
    var imageHref: String?,
    @JsonProperty("title")
    var title: String?
)