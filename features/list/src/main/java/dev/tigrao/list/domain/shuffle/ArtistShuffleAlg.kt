package dev.tigrao.list.domain.shuffle

import dev.tigrao.list.entity.ListVO
import java.util.LinkedList

internal class ArtistShuffleAlg : ShuffleAlg<ListVO> {

    override fun shuffle(list: List<ListVO>): List<ListVO> {
        val mutableList = list.toMutableList()
        val itemMap = mutableList.toMutableMap()

        val linkedList = LinkedList<Pair<String, LinkedList<ListVO>>>(itemMap.toList())
        linkedList.strongShuffle()

        return shuffleList(linkedList)
    }

    private fun shuffleList(linkedList: LinkedList<Pair<String, LinkedList<ListVO>>>): List<ListVO> {
        val resultList = mutableListOf<ListVO>()
        var last: ListVO? = null

        while (linkedList.isNotEmpty()) {
            val (currentArtistKey, currentMusicList) = linkedList.pop()

            if (currentMusicList.peek().artistName != last?.artistName || linkedList.isEmpty()) {
                last = getMusic(currentMusicList)
            } else {
                val (nextArtistKey, aHeadMusicList) = linkedList.pop()

                last = getMusic(aHeadMusicList)

                linkedList.addDestructedItemsAgain(nextArtistKey to aHeadMusicList)
            }

            linkedList.addDestructedItemsAgain(currentArtistKey to currentMusicList)

            resultList.add(last)
        }

        return resultList
    }

    private fun LinkedList<Pair<String, LinkedList<ListVO>>>.addDestructedItemsAgain(pair: Pair<String, LinkedList<ListVO>>) {
        if (pair.second.isNotEmpty()) {
            add(pair)
        }
    }

    private fun getMusic(list: LinkedList<ListVO>): ListVO {
        list.strongShuffle()
        return list.pop()
    }

    private fun <T> MutableList<T>.strongShuffle() {
        this.shuffle()
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
