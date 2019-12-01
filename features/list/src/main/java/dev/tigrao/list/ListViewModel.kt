package dev.tigrao.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.tigrao.commons.statemachine.StateEvent
import dev.tigrao.commons.statemachine.StateMachine
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable

internal class ListViewModel(
    private val useCase: ListUseCase,
    private val stateMachine: StateMachine<List<ListVO>>,
    private val observerScheduler: Scheduler
) : ViewModel() {

    private val _liveData = MutableLiveData<StateEvent<List<ListVO>>>()
    val liveData : LiveData<StateEvent<List<ListVO>>> = _liveData
    private var disposable: Disposable? = null

    fun fetchSongs() {

        disposable = useCase
            .fetchSongs()
            .compose(stateMachine)
            .observeOn(observerScheduler)
            .subscribe {
                _liveData.value = it
            }
    }

    override fun onCleared() {
        super.onCleared()

        disposable?.dispose()
    }
}
