package dev.tigrao.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

internal class NetworkService(
    private val networkBuilder: NetworkBuilder,
    private val okhttpClientFactory: OkhttpClientFactory
) {

    fun createRetrofitInstance(): Retrofit =
        Retrofit.Builder()
            .baseUrl(networkBuilder.baseUrl)
            .client(okhttpClientFactory.createNewInstance())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
}
