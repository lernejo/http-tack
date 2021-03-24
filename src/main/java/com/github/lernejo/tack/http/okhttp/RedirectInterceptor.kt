package com.github.lernejo.tack.http.okhttp

import com.github.lernejo.tack.http.HttpTack
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

class RedirectInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return if (HttpTack.isEnabled) {
            val originalRequest = chain.request()
            val url = originalRequest.url()
            val redirectedRequest = originalRequest.newBuilder()
                .addHeader("tack-original-url", extractRootUrl(url))
                .url(
                    url.newBuilder().scheme("http").host(HttpTack.redirectHost).port(HttpTack.redirectPort)
                        .build()
                )
                .build()
            chain.proceed(redirectedRequest)
        } else {
            chain.proceed(chain.request())
        }
    }

    private fun extractRootUrl(url: HttpUrl): String {
        val pathStart = url.toString().indexOf('/', url.scheme().length + 3)
        return url.toString().substring(0, pathStart)
    }
}
