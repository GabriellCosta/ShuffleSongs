package dev.tigrao.list

import com.google.gson.annotations.SerializedName

internal class ListResponseDTO(
    @SerializedName("results")
    val result : List<ListResultItemDTO>
)
