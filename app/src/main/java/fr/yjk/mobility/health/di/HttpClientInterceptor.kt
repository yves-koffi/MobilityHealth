package fr.yjk.mobility.health.di

import android.content.Context
import fr.yjk.mobility.health.network.config.SessionKeys
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


val LAST_TIME_HEADER_SYNC = listOf<String>(
    "X-Company-Last",
)

class HttpClientInterceptor(private val context: Context) : Interceptor {

    private val sessionKeys = SessionKeys(context)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()/* if (originalRequest.body == null || originalRequest.header("Content-Encoding") != null) {
             return chain.proceed(originalRequest)
         }*/


        val compressedRequest: Request.Builder =
            originalRequest.newBuilder().header("Accept", "application/json")
                .header("Content-Type", "application/json")


        /*for (headerKey in LAST_TIME_HEADER_SYNC) {
            if (originalRequest.header(headerKey) != null) {
                compressedRequest.header(
                    headerKey,
                    sessionKeys.find(headerKey) ?: "2024-06-18T10:37:09.732481"
                )
            }
        }*/

        if (originalRequest.url.toUrl().path.startsWith("/api/auth")) {
            sessionKeys.findToken()?.let { token ->
                compressedRequest.header("Authorization", "Bearer $token")
            }

        }
        return chain.proceed(compressedRequest.build())
    }

}
