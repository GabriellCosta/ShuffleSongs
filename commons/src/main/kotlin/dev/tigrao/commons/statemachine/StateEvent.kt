package dev.tigrao.commons.statemachine

sealed class StateEvent<out VALUE>

object StartedEvent : StateEvent<Nothing>()

data class ErrorEvent(val errorDataDTO: ErrorDataDTO) : StateEvent<Nothing>()

data class SuccessEvent<RESULT>(val result: RESULT) : StateEvent<RESULT>()

object FinishedEvent : StateEvent<Nothing>()
