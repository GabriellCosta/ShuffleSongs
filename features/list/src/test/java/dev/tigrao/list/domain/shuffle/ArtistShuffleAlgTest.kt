package dev.tigrao.list.domain.shuffle

import dev.tigrao.list.entity.ListVO
import org.junit.Assert.assertNotEquals
import org.junit.Ignore
import org.junit.Test

class ArtistShuffleAlgTest {

    @Test
    fun givenSimplesList_WithMoreItemsThanOne_ShouldShuffle() {
        val initialList = listOf(
            ListVO("music", null, "Tiger"),
            ListVO("music", null, "Bruno"),
            ListVO("music", null, "Tiger")
        )

        val result = ArtistShuffleAlg(FisherYatesAlg()).shuffle(initialList)

        assertThatNotSameArtistInOrder(result)
    }

    @Test
    fun givenList_ShouldBeShuffled() {
        val initialList = listOf(
            ListVO("music", null, "Tiger"),
            ListVO("music", null, "Tiger"),
            ListVO("music", null, "Bruno"),
            ListVO("music", null, "Android"),
            ListVO("music", null, "Bruno")
        )

        val result = ArtistShuffleAlg(FisherYatesAlg()).shuffle(initialList)

        assertThatNotSameArtistInOrder(result)
    }

    @Test
    fun givenListAsAPIResponse_ShouldBeShuffled() {
        val initialList = listOf(
            ListVO("music1", null, "Tiger"),
            ListVO("music2", null, "Tiger"),
            ListVO("music3", null, "Tiger"),
            ListVO("music4", null, "Tiger"),
            ListVO("music5", null, "Tiger"),
            ListVO("music6", null, "Bruno"),
            ListVO("music7", null, "Bruno"),
            ListVO("music8", null, "Bruno"),
            ListVO("music9", null, "Bruno"),
            ListVO("music10", null, "Bruno"),
            ListVO("music11", null, "Android"),
            ListVO("music12", null, "Android"),
            ListVO("music13", null, "Android"),
            ListVO("music14", null, "Android"),
            ListVO("music15", null, "Android"),
            ListVO("music16", null, "Thiago"),
            ListVO("music17", null, "Thiago"),
            ListVO("music18", null, "Thiago"),
            ListVO("music19", null, "Thiago"),
            ListVO("music20", null, "Thiago")
        )

        val result = ArtistShuffleAlg(FisherYatesAlg()).shuffle(initialList)

        assertThatNotSameArtistInOrder(result)
    }

    @Test
    @Ignore("Take to long to run")
    fun givenAVeryBigList_ShouldBeShuffled() {
        val parameterList = mutableListOf<ListVO>()
        val numberOfItems = 1000

        for (item in 0..numberOfItems) {
            for (inner in 0..numberOfItems) {
                parameterList.add(ListVO("music$inner", null, "Tiger$item"))
            }
        }

        val result = ArtistShuffleAlg(FisherYatesAlg()).shuffle(parameterList)

        assertThatNotSameArtistInOrder(result)
    }

    private fun assertThatNotSameArtistInOrder(list: List<ListVO>) {
        for (item in list.indices) {
            if (item < list.indices.last)
                assertNotEquals(list[item].artistName, list[item + 1].artistName)
        }
    }
}
