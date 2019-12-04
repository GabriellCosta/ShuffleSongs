package dev.tigrao.list.domain.shuffle

import dev.tigrao.list.entity.ListVO
import java.util.LinkedList

internal class ArtistShuffleAlg : ShuffleAlg<ListVO> {

    override fun shuffle(list: List<ListVO>): List<ListVO> {
        val mutableList = list.toMutableList()

        val itemMap = mutableList.toMutableMap()

        val linkedList = LinkedList<Pair<String, LinkedList<ListVO>>>(itemMap.toList())
        linkedList.strongShuffle()

        val resultList = mutableListOf<ListVO>()
        var last: ListVO? = null

        while (linkedList.isNotEmpty()) {
            val (currentArtistKey, currentMusicList) = linkedList.pop()

            if (currentMusicList.peek().artistName != last?.artistName || linkedList.isEmpty()) {
                currentMusicList.strongShuffle()
                last = currentMusicList.pop()
            } else {
                val (nextArtistKey, aHeadMusicList) = linkedList.pop()
                aHeadMusicList.strongShuffle()
                last = aHeadMusicList.pop()
                if (aHeadMusicList.isNotEmpty()) {
                    linkedList.add(nextArtistKey to aHeadMusicList)
                }
            }
            resultList.add(last)
            if (currentMusicList.isNotEmpty()) {
                linkedList.add(currentArtistKey to currentMusicList)
            }
        }

        return resultList
    }

    private fun <T> List<T>.strongShuffle() {
        this.shuffled()
    }

    private fun List<ListVO>.toMutableMap(): MutableMap<String, LinkedList<ListVO>> {
        val itemMap = mutableMapOf<String, LinkedList<ListVO>>()

        forEach {
            val listItems = itemMap[it.artistName] ?: LinkedList()

            listItems.push(it)
            itemMap[it.artistName] = listItems
        }

        return itemMap
    }
}
