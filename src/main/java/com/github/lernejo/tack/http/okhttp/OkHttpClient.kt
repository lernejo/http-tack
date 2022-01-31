package com.github.lernejo.tack.http.okhttp

import okhttp3.*
import java.net.Proxy
import java.net.ProxySelector
import java.time.Duration
import java.util.concurrent.TimeUnit
import javax.net.SocketFactory
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

class OkHttpClient @JvmOverloads constructor(builder: okhttp3.OkHttpClient.Builder = okhttp3.OkHttpClient.Builder()) : OverridableOkHttpClient(enhance(builder)) {

    companion object {
        private fun enhance(builder: okhttp3.OkHttpClient.Builder): okhttp3.OkHttpClient.Builder {
            return builder.addInterceptor(RedirectInterceptor())
        }
    }

    class Builder {
        fun build() = OkHttpClient()

        private val interceptors: List<Interceptor> = ArrayList()
        private val networkInterceptors: List<Interceptor> = ArrayList()
        fun callTimeout(timeout: Long, unit: TimeUnit?) = this
        fun callTimeout(duration: Duration) = this
        fun connectTimeout(timeout: Long, unit: TimeUnit?) = this
        fun connectTimeout(duration: Duration) = this
        fun readTimeout(timeout: Long, unit: TimeUnit?) = this
        fun readTimeout(duration: Duration) = this
        fun writeTimeout(timeout: Long, unit: TimeUnit?) = this
        fun writeTimeout(duration: Duration) = this
        fun pingInterval(interval: Long, unit: TimeUnit?) = this
        fun pingInterval(duration: Duration) = this
        fun proxy(proxy: Proxy?) = this
        fun proxySelector(proxySelector: ProxySelector?) = this
        fun cookieJar(cookieJar: CookieJar?) = this
        fun cache(cache: Cache?) = this
        fun dns(dns: Dns?) = this
        fun socketFactory(socketFactory: SocketFactory?) = this
        fun sslSocketFactory(sslSocketFactory: SSLSocketFactory?) = this
        fun sslSocketFactory(sslSocketFactory: SSLSocketFactory?, trustManager: X509TrustManager?) = this
        fun hostnameVerifier(hostnameVerifier: HostnameVerifier?) = this
        fun certificatePinner(certificatePinner: CertificatePinner?) = this
        fun authenticator(authenticator: Authenticator?) = this
        fun proxyAuthenticator(proxyAuthenticator: Authenticator?) = this
        fun connectionPool(connectionPool: ConnectionPool?) = this
        fun followSslRedirects(followProtocolRedirects: Boolean) = this
        fun followRedirects(followRedirects: Boolean) = this
        fun retryOnConnectionFailure(retryOnConnectionFailure: Boolean) = this
        fun dispatcher(dispatcher: Dispatcher?) = this
        fun protocols(protocols: MutableList<Protocol?>) = this
        fun connectionSpecs(connectionSpecs: List<ConnectionSpec>?) = this
        fun interceptors() = interceptors
        fun addInterceptor(interceptor: Interceptor?) = this
        fun networkInterceptors() = networkInterceptors
        fun addNetworkInterceptor(interceptor: Interceptor?) = this
        fun eventListener(eventListener: EventListener?) = this
        fun eventListenerFactory(eventListenerFactory: EventListener.Factory?) = this
    }
}
