package dev.tigrao.list.shuffle

internal interface ShuffleAlg<T> {

    fun shuffle(list: List<T>) : List<T>
}
