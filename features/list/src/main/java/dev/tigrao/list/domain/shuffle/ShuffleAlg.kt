package dev.tigrao.list.domain.shuffle

internal interface ShuffleAlg<T> {

    fun shuffle(list: List<T>) : List<T>
}
