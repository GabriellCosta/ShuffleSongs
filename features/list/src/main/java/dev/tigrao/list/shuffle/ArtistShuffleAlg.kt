package dev.tigrao.list.shuffle

import dev.tigrao.list.ListVO

internal class ArtistShuffleAlg(private val shuffleAlg: ShuffleAlg<ListVO>) : ShuffleAlg<ListVO> {

    override fun shuffle(list: List<ListVO>): List<ListVO> {
        return preShuffle(list)
    }

    private fun preShuffle(list: List<ListVO>) = shuffleAlg.shuffle(list)
}
