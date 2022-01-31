package com.github.lernejo.tack.http.okhttp

import com.github.lernejo.tack.http.retrofit.TackRetrofit
import org.junit.jupiter.api.Test

class OkHttpClientTest {

    @Test
    fun binary_compatibility() {
        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .build()

        val retrofit = TackRetrofit.Builder()
            .baseUrl("http://localhost:7080/")
            .client(okHttpClient)
            .build()
    }
}
