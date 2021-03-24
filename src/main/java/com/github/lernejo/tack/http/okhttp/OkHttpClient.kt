package com.github.lernejo.tack.http.okhttp

import okhttp3.OverridableOkHttpClient

class OkHttpClient @JvmOverloads constructor(builder: Builder = Builder()) : OverridableOkHttpClient(enhance(builder)) {
    companion object {
        private fun enhance(builder: Builder): Builder {
            return builder.addInterceptor(RedirectInterceptor())
        }
    }
}
