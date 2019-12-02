package dev.tigrao.list.domain.shuffle

import org.junit.Test

class FisherYatesAlgTest {

    @Test
    fun shouldShuffle() {
        val alg = FisherYatesAlg<String>()

        val result = alg.shuffle(listOf("Gabriel", "Tigrao", "Bruno", "Trança"))

        print(result)
    }
}
