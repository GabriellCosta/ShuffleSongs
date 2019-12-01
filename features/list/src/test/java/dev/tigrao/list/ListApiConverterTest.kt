package dev.tigrao.list

import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.URL

class ListApiConverterTest {

    @Test
    fun givenTrack_ShouldConvertToListVO_WithCorrectAlbumArt() {
        val trackItem = ListResultItemDTO(
            1,
            "tiger",
            "tiger",
            URL("http://google.com"),
            "My little Tiger"
        )

        val result = ListApiConverter().map(trackItem)

        assertEquals("http://google.com", result.albumArt)
    }

    @Test
    fun givenTrack_ShouldConvertToListVO_WithArtistName() {
        val trackItem = ListResultItemDTO(
            1,
            "tiger",
            "Rock",
            URL("http://google.com"),
            "My little Tiger"
        )

        val result = ListApiConverter().map(trackItem)

        assertEquals("tiger (Rock)", result.artistName)
    }

    @Test
    fun givenTrack_ShouldConvertToListVO_WithTrackNAme() {
        val trackItem = ListResultItemDTO(
            1,
            "tiger",
            "tiger",
            URL("http://google.com"),
            "My little Tiger"
        )

        val result = ListApiConverter().map(trackItem)

        assertEquals("My little Tiger", result.musicName)
    }
}
