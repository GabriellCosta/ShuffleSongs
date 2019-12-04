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
            val (key, list1) = linkedList.pop()

            if (list1.peek().artistName != last?.artistName || linkedList.isEmpty()) {
                list1.strongShuffle()
                last = list1.pop()
            } else {
                val (key2, list2) = linkedList.pop()
                list2.strongShuffle()
                last = list2.pop()
                if (list2.isNotEmpty()) {
                    linkedList.add(key2 to list2)
                }
            }
            resultList.add(last)
            if (list1.isNotEmpty()) {
                linkedList.add(key to list1)
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
