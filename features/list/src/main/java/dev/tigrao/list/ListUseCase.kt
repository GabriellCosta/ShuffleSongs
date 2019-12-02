package dev.tigrao.list

import dev.tigrao.list.shuffle.ShuffleAlg
import io.reactivex.Observable
import io.reactivex.Scheduler

internal class ListUseCase(
    private val scheduler: Scheduler,
    private val listApi: ListApi,
    private val listApiConverter: ListApiConverter,
    private val shuffleAlg: ShuffleAlg<ListVO>
) {

    fun fetchSongs(): Observable<List<ListVO>> =
        listApi.fetchSongs(createInnerArtistList())
            .subscribeOn(scheduler)
            .map { it.result }
            .flatMapIterable { it }
            .filter { it.trackName != null }
            .cast(TrackResultDTO::class.java)
            .map { listApiConverter.map(it) }
            .toList()
            .toObservable()

    fun shuffle(list: List<ListVO>) = shuffleAlg.shuffle(list)

    private fun createInnerArtistList() = "909253,1171421960,358714030,1419227,264111789"
}
