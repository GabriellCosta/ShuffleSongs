package dev.tigrao.network.di

import dev.tigrao.network.NetworkService
import dev.tigrao.network.OkhttpClientFactory
import org.koin.dsl.module

val networkModule = module {

    single {
        NetworkService(get(), get())
            .createRetrofitInstance()
    }

    single {
        OkhttpClientFactory()
    }
}
