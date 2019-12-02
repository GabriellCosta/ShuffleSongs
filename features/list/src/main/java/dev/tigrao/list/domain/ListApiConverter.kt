package dev.tigrao.list.domain

import dev.tigrao.list.entity.ListVO
import dev.tigrao.list.entity.TrackResultDTO

class ListApiConverter {

    fun map(listResultItemDTO: TrackResultDTO): ListVO {
        return ListVO(
            albumArt = listResultItemDTO.artworkUrl.toString(),
            musicName = listResultItemDTO.trackName!!,
            artistName = "${listResultItemDTO.artistName} (${listResultItemDTO.primaryGenreName})"
        )
    }
}
