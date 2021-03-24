package com.github.lernejo.tack.http.retrofit

import okhttp3.Call
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import java.net.URL
import java.util.concurrent.Executor

class TackRetrofit {
    class Builder {
        private val builder = Retrofit.Builder()

        fun client(client: OkHttpClient): Builder {
            builder.client(client)
            return this
        }

        fun callFactory(factory: Call.Factory): Builder {
            builder.callFactory(factory)
            return this
        }

        fun baseUrl(baseUrl: URL): Builder {
            builder.baseUrl(baseUrl)
            return this
        }

        fun baseUrl(baseUrl: String): Builder {
            builder.baseUrl(baseUrl)
            return this
        }

        fun baseUrl(baseUrl: HttpUrl): Builder {
            builder.baseUrl(baseUrl)
            return this
        }

        fun addConverterFactory(factory: Converter.Factory): Builder {
            builder.addConverterFactory(factory)
            return this
        }

        fun addCallAdapterFactory(factory: CallAdapter.Factory): Builder {
            builder.addCallAdapterFactory(factory)
            return this
        }

        fun callbackExecutor(executor: Executor): Builder {
            builder.callbackExecutor(executor)
            return this
        }

        fun callAdapterFactories(): List<CallAdapter.Factory> {
            return builder.callAdapterFactories()
        }

        fun converterFactories(): List<Converter.Factory> {
            return builder.converterFactories()
        }

        fun validateEagerly(validateEagerly: Boolean): Builder {
            builder.validateEagerly(validateEagerly)
            return this
        }

        fun build(): Retrofit {
            return builder
                .client(com.github.lernejo.tack.http.okhttp.OkHttpClient())
                .build()
        }
    }
}
