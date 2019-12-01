package dev.tigrao.list

import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.URL

class ListUseCaseTest {

    private val listApi = mockk<ListApi>()
    private val useCase = ListUseCase(Schedulers.trampoline(), listApi, ListApiConverter())

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
}
