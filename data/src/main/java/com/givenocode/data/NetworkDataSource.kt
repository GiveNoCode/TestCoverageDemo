package com.givenocode.data

import com.givenocode.domain.DataSource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.jetbrains.annotations.TestOnly
import java.io.IOException

class NetworkDataSource : DataSource {

    private var url = "https://alexnormand-dino-ipsum.p.rapidapi.com"

    override suspend fun getData(): String = withContext(IO) {
        val client = OkHttpClient()

        val request: Request = Request.Builder()
            .url("$url/?format=html&words=5&paragraphs=1")
            .get()
            .addHeader("x-rapidapi-host", "alexnormand-dino-ipsum.p.rapidapi.com")
            .addHeader("x-rapidapi-key", "3aea28b3a5mshde2dab08dc8dbefp1cbca0jsn887b21ccd139")
            .build()

        val response: Response = client.newCall(request).execute()
        if (response.isSuccessful) {
            response.body!!.string().replace("<p>", "").replace("</p>", "")
        } else {
            throw IOException("Network Error")
        }
    }

    @TestOnly
    fun setUrl(url: String) {
        this.url = url
    }
}