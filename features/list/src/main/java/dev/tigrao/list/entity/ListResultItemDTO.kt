package dev.tigrao.list.entity

import com.google.gson.annotations.SerializedName
import java.net.URL

class ListResultItemDTO(
    @SerializedName("id")
    override val id: Long,
    @SerializedName("artistName")
    override val artistName: String,
    @SerializedName("primaryGenreName")
    override val primaryGenreName: String,
    @SerializedName("artworkUrl")
    override val artworkUrl: URL?,
    @SerializedName("trackName")
    override val trackName: String?
) : TrackResultDTO

interface ArtistOnlyResultDTO {
    val id: Long
    val artistName: String
    val primaryGenreName: String
}

interface TrackResultDTO : ArtistOnlyResultDTO {
    val artworkUrl: URL?
    val trackName: String?
}
