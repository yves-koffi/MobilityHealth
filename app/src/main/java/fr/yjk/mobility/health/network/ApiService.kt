package fr.yjk.mobility.health.network

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface ApiService : AuthenticatedService, RegisteredService {

    @Multipart
    @POST("api/upload/file")
    suspend fun uploadFile(
        @Part("imageable_type") model: RequestBody,
        @Part("imageable_id") ref: RequestBody,
        @Part image: MultipartBody.Part
    ): List<String>

}




