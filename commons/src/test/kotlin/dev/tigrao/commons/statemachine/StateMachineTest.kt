package dev.tigrao.commons.statemachine

import dev.tigrao.commons.statemachine.ErrorDataDTO
import dev.tigrao.commons.statemachine.ErrorEvent
import dev.tigrao.commons.statemachine.FinishedEvent
import dev.tigrao.commons.statemachine.StartedEvent
import dev.tigrao.commons.statemachine.StateMachine
import dev.tigrao.commons.statemachine.SuccessEvent
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Test
import java.lang.IllegalArgumentException

class StateMachineTest {

    val stateMachine =
        StateMachine<String>(Schedulers.trampoline())

    @Test
    fun givenBasicStream_ShouldEmmitWithSuccess() {
        val name = "Tiger"
        val emittedSequence = listOf(
            StartedEvent,
            SuccessEvent(name),
            FinishedEvent
        )

        val result = Observable.just(name).compose(stateMachine)

        result.test()
            .assertValueSequence(emittedSequence)
    }

    @Test
    fun givenErrorStream_ShouldEmmitWithFailure() {
        val exception = IllegalArgumentException("Invalid Parameter")
        val emittedSequence =
            listOf(
                StartedEvent,
                ErrorEvent(ErrorDataDTO(exception)),
                FinishedEvent
            )

        val result =
            Observable.error<String>(exception)
                .compose(stateMachine)

        result.test()
            .assertValueSequence(emittedSequence)
    }

    @Test
    fun givenEmptyStream_ShouldEmmitWithFailure() {
        val emittedSequence =
            listOf(
                StartedEvent,
                FinishedEvent
            )

        val result =
            Observable.empty<String>().compose(stateMachine)

        result.test()
            .assertValueSequence(emittedSequence)
    }
}
