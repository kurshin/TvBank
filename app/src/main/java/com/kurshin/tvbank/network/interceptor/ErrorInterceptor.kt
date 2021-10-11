package com.kurshin.tvbank.network.interceptor

import android.annotation.SuppressLint
import android.util.Log
import com.google.gson.JsonParser
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.nio.charset.Charset
import javax.inject.Inject
import javax.inject.Singleton
import javax.xml.parsers.DocumentBuilderFactory

@Singleton
class ErrorInterceptor @Inject constructor(
    private val jsonParser: JsonParser
) : Interceptor {

    @SuppressLint("DefaultLocale")
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val response: Response = chain.proceed(request)
        val body = response.body

        if (body?.contentType() != null && body.contentType()!!.subtype.toLowerCase() == "xml") {
            var errorMessage = ""
            var success = true
            try {
                val source = body.source()
                source.request(Long.MAX_VALUE)
                // Clone(!) the existing buffer is they can only read once so we still want to pass the original one to the chain.
                val json: String = source.buffer.clone()
                    .readString(body.contentType()!!.charset(Charset.forName("UTF-8"))!!)

                val builderFactory = DocumentBuilderFactory.newInstance()
                val docBuilder = builderFactory.newDocumentBuilder()
                val doc = docBuilder.parse(source.buffer.clone().inputStream())

                val responseElement = doc.getElementsByTagName("error")
                if (responseElement.length > 0) {
                    errorMessage = responseElement.item(0).attributes.item(0).nodeValue
                    success = false
                }
            } catch (t: Throwable) {
                Log.i("1111", "Error parsing response from server: ", t)
            }
            // Check if status success then throw and exception so retrofit can trigger the onFailure callback method.
            if (!success) {
                throw IOException(errorMessage)
            }
        }
        return response
    }
}