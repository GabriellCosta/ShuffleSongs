package dev.tigrao.list.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.tigrao.commons.statemachine.StateEvent
import dev.tigrao.commons.statemachine.StateMachine
import dev.tigrao.commons.statemachine.SuccessEvent
import dev.tigrao.list.domain.ListUseCase
import dev.tigrao.list.entity.ListVO
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable

internal class ListViewModel(
    private val useCase: ListUseCase,
    private val stateMachine: StateMachine<List<ListVO>>,
    private val observerScheduler: Scheduler
) : ViewModel() {

    private var disposable: Disposable? = null

    private val _liveData = MutableLiveData<StateEvent<List<ListVO>>>()
    val liveData : LiveData<StateEvent<List<ListVO>>> = _liveData

    private val listVO = mutableListOf<ListVO>()

    fun fetchSongs() {

        disposable = useCase
            .fetchSongs()
            .compose(stateMachine)
            .observeOn(observerScheduler)
            .doAfterNext {
                if (it is SuccessEvent) {
                    listVO.clear()
                    listVO.addAll(it.result)
                }
            }
            .subscribe {
                _liveData.value = it
            }
    }

    override fun onCleared() {
        super.onCleared()

        disposable?.dispose()
    }

    fun shuffle() {
        val shuffled = useCase.shuffle(listVO)

        _liveData.value = SuccessEvent(shuffled)
    }
}
