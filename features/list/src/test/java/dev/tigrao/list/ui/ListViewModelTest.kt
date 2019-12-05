package dev.tigrao.list.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import dev.tigrao.commons.statemachine.FinishedEvent
import dev.tigrao.commons.statemachine.StateEvent
import dev.tigrao.commons.statemachine.StateMachine
import dev.tigrao.commons.statemachine.SuccessEvent
import dev.tigrao.list.domain.ListUseCase
import dev.tigrao.list.entity.ListVO
import dev.tigrao.list.ui.ListViewModel
import io.mockk.Ordering
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Rule
import org.junit.Test

class ListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun whenFetchSongs_ShouldEmitStateEvent() {
        val useCase = mockk<ListUseCase>()
        every { useCase.fetchSongs() } returns Observable.just(listOf())
        val viewModel =
            ListViewModel(
                useCase,
                StateMachine(Schedulers.trampoline()),
                Schedulers.trampoline()
            )
        val observer = mockk<Observer<in StateEvent<List<ListVO>>>>()
        viewModel.liveData.observeForever(observer)

        viewModel.fetchSongs()

        verify { observer.onChanged(any()) }
    }

    @Test
    fun whenShuffle_ShouldUseUseCaseLogic() {
        val useCase = mockk<ListUseCase>(relaxed = true)
        val viewModel =
            ListViewModel(
                useCase,
                StateMachine(Schedulers.trampoline()),
                Schedulers.trampoline()
            )

        viewModel.shuffle()

        verify { useCase.shuffle(any()) }
    }

    @Test
    fun whenShuffle_ShouldUpdateUiWithShuffledList() {
        val useCase = mockk<ListUseCase>(relaxed = true)
        val responseMockList = emptyList<ListVO>()
        every { useCase.shuffle(any()) } returns responseMockList
        val viewModel =
            ListViewModel(
                useCase,
                StateMachine(Schedulers.trampoline()),
                Schedulers.trampoline()
            )

        val observer = mockk<Observer<in StateEvent<List<ListVO>>>>(relaxed = true)
        viewModel.liveData.observeForever(observer)

        viewModel.shuffle()

        verify { observer.onChanged(eq(SuccessEvent(responseMockList))) }
    }

    @Test
    fun whenSongsAlreadyFetched_ShouldNotFetchAgainFromUseCase() {
        val useCase = mockk<ListUseCase>()
        every { useCase.fetchSongs() } returns Observable.just(listOf(mockk(relaxed = true)))
        val viewModel =
            ListViewModel(
                useCase,
                StateMachine(Schedulers.trampoline()),
                Schedulers.trampoline()
            )

        viewModel.fetchSongs()
        viewModel.fetchSongs()

        verify(exactly = 1) { useCase.fetchSongs() }
    }

    @Test
    fun whenSongsAlreadyFetched_ShouldSendAlreadySavedList() {
        val useCase = mockk<ListUseCase>()
        val mockList = listOf<ListVO>(mockk(relaxed = true))
        every { useCase.fetchSongs() } returns Observable.just(mockList)
        val viewModel =
            ListViewModel(
                useCase,
                StateMachine(Schedulers.trampoline()),
                Schedulers.trampoline()
            )

        val observer = mockk<Observer<in StateEvent<List<ListVO>>>>(relaxed = true)
        viewModel.liveData.observeForever(observer)

        viewModel.fetchSongs()
        viewModel.fetchSongs()

        verify {
            observer.onChanged(eq(SuccessEvent(mockList)))
            observer.onChanged(FinishedEvent)
        }
    }
}
