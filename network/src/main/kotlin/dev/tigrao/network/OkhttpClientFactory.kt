package dev.tigrao.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

internal class OkhttpClientFactory {

    fun createNewInstance(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(createLoggerInterceptor())
            .build()

    private fun createLoggerInterceptor(): Interceptor =
        HttpLoggingInterceptor()
            .also {
                it.level = HttpLoggingInterceptor.Level.HEADERS
            }
}
