package dev.tigrao.list

class ListApiConverter {

    fun map(listResultItemDTO: TrackResultDTO): ListVO {
        return ListVO(
            albumArt = listResultItemDTO.artworkUrl.toString(),
            musicName = listResultItemDTO.trackName!!,
            artistName = "${listResultItemDTO.artistName} (${listResultItemDTO.primaryGenreName})"
        )
    }
}
