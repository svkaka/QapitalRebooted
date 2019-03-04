package com.ovrbach.qapitalchallengerebooted.remote.util

import okhttp3.*
import okio.Buffer

import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream

/**
 * This [Interceptor] gets plugged into Retrofit's okhttp client and is used to return local
 * JSON data for unit testing instead of going over the network.
 */
class UnitTestingHttpInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestedUrl = request.url()
        var requestedPath = requestedUrl.encodedPath()
        val requestedMethod = request.method()

        if (!requestedPath.endsWith("/")) {
            requestedPath = "$requestedPath/"
        }

        val fixtureJsonPath = "$FIXTURES_DIR$requestedPath$requestedMethod.json"
        val inputStream = FileInputStream(fixtureJsonPath)
        val input = Buffer().readFrom(inputStream)

        val mediaType = MediaType.parse("application/json")
        val responseBody = ResponseBody.create(mediaType, input.size(), input)

        return Response.Builder()
            .request(request)
            .protocol(Protocol.HTTP_2)
            .code(200)
            .body(responseBody)
            .message("")
            .build()
    }

    companion object {

        private val FIXTURES_DIR = "src/test/fixtures"
    }
}
