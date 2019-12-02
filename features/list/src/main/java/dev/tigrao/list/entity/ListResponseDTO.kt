package dev.tigrao.list.entity

import com.google.gson.annotations.SerializedName

internal class ListResponseDTO(
    @SerializedName("results")
    val result : List<ListResultItemDTO>
)
