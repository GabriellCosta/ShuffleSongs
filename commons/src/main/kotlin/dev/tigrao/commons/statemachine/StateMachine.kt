package dev.tigrao.commons.statemachine

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler

class StateMachine<T>(private val uiScheduler: Scheduler) :
    ObservableTransformer<T, StateEvent<T>> {

    override fun apply(upstream: Observable<T>): Observable<StateEvent<T>> =
        upstream
            .map { value: T -> SuccessEvent(value) as StateEvent<T> }
            .onErrorReturn { error: Throwable ->
                ErrorEvent(
                    ErrorDataDTO(error)
                )
            }
            .startWith(StartedEvent)
            .concatWith(Observable.just(FinishedEvent))
            .observeOn(uiScheduler)
}
