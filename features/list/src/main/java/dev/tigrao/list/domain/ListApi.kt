package dev.tigrao.list.domain

import dev.tigrao.list.entity.ListResponseDTO
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

internal interface ListApi {

    @GET("/lookup")
    fun fetchSongs(@Query("id") ids: String): Observable<ListResponseDTO>
}
