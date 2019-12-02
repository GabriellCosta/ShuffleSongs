package dev.tigrao.list.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import dev.tigrao.commons.statemachine.StateEvent
import dev.tigrao.commons.statemachine.StateMachine
import dev.tigrao.list.domain.ListUseCase
import dev.tigrao.list.entity.ListVO
import dev.tigrao.list.ui.ListViewModel
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
}
