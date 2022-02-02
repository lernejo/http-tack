package com.github.lernejo.tack.http

import com.github.lernejo.tack.http.okhttp.OkHttpClient
import com.github.lernejo.tack.http.retrofit.TackRetrofit
import java.nio.file.Files
import java.nio.file.Path

object HttpTack {

    @JvmStatic
    fun installOnSources(path: Path) {
        path.toFile().walk()
            .map { it.toPath() }
            .filter { p -> Files.isRegularFile(p) }
            .filter { p -> p.fileName.toString().endsWith(".java") }
            .forEach { installOnSource(it) }

    }

    @JvmStatic
    fun installOnSource(path: Path) {
        val content = String(Files.readAllBytes(path))

        val modifiedCode = content
            .replace(
                Regex("import\\s+okhttp3\\.OkHttpClient;"),
                "import ${OkHttpClient::class.java.name};"
            )
            .replace(
                Regex("import\\s+retrofit2\\.Retrofit;"),
                "import retrofit2.Retrofit;\nimport ${TackRetrofit::class.java.name};"
            )
            .replace(
                Regex("import\\s+retrofit2\\.\\*\\s*;"),
                "import retrofit2.*;\nimport ${TackRetrofit::class.java.name};"
            )
            .replace(
                Regex("import\\s+retrofit2\\.Retrofit.Builder;"),
                "import ${TackRetrofit.Builder::class.java.name};"
            )
            .replace(Regex("new\\s+Retrofit."), "new TackRetrofit.")

        Files.write(path, modifiedCode.toByteArray())
    }

    @JvmStatic
    val version: String by lazy {
        HttpTack::class.java.`package`.implementationVersion
    }

    val isEnabled: Boolean by lazy {
        System.getProperty("tackEnabled", "false").toBoolean()
    }

    val redirectHost: String
        get() = "localhost"

    val redirectPort: Int by lazy {
        System.getProperty("tackRedirectPort", "9876").toInt()
    }
}
