package dev.tigrao.list

import dev.tigrao.list.shuffle.ShuffleAlg
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.URL

class ListUseCaseTest {

    private val listApi = mockk<ListApi>()
    private val shuffleAlg = mockk<ShuffleAlg<ListVO>>(relaxed = true)
    private val useCase = ListUseCase(Schedulers.trampoline(), listApi, ListApiConverter(),
        shuffleAlg
    )

    @Test
    fun givenSuccessApiResponse_ShouldEmitNoErrors() {
        every { listApi.fetchSongs(any()) } returns Observable.just(ListResponseDTO(listOf()))

        useCase.fetchSongs()
            .test()
            .assertNoErrors()
    }

    @Test
    fun givenSuccessApiResponse_ShouldOnlyEmitTracks() {
        val noTrackItem = ListResultItemDTO(1,  "tiger", "tiger", null, null)
        val trackItem = ListResultItemDTO(
            1,
            "tiger",
            "tiger",
            URL("http://google.com"),
            "My little Tiger"
        )
        every { listApi.fetchSongs(any()) } returns Observable.just(
            ListResponseDTO(
                listOf(noTrackItem, trackItem, trackItem)
            )
        )

        val result = useCase.fetchSongs()
            .test()
            .values()
            .first()

        assertEquals(2, result.size)
    }

    @Test
    fun whenShuffle_ShouldUseShuffleAlgorithm() {
        val list = emptyList<ListVO>()

        useCase.shuffle(list)

        verify { shuffleAlg.shuffle(list) }
    }
}
