package dev.tigrao.shufflesongs

import dev.tigrao.network.NetworkBuilder
import dev.tigrao.network.di.networkModule
import org.koin.core.context.startKoin
import org.koin.dsl.module

internal class KoinModules {

    fun start() {
        startKoin {
            modules(networkModule)

            modules(module {
                single {
                    NetworkBuilder(BuildConfig.API_URL)
                }
            })
        }
    }
}
