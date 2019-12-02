package dev.tigrao.list.shuffle

import java.util.Random
import java.util.concurrent.ThreadLocalRandom

internal class FisherYatesAlg<T> : ShuffleAlg<T> {

    override fun shuffle(list: List<T>): List<T> {
        val mutable = mutableListOf<T>()
        mutable.addAll(list)

        val rnd: Random = ThreadLocalRandom.current()
        for (i in mutable.size - 1 downTo 1) {
            val index: Int = rnd.nextInt(i + 1)
            val a: T = mutable[index]

            mutable[index] = mutable[i]
            mutable[i] = a
        }

        return mutable
    }
}
