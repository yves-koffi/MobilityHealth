package fr.yjk.mobility.health.di


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import fr.yjk.mobility.health.data.login.LoginRepository
import fr.yjk.mobility.health.data.login.impl.LoginRepositoryImpl
import fr.yjk.mobility.health.data.register.RegisterRepository
import fr.yjk.mobility.health.data.register.impl.RegisterRepositoryImpl
import fr.yjk.mobility.health.network.ApiService
import fr.yjk.mobility.health.network.config.SessionKeys
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface MainAppModule {

    @Binds
    @Singleton
    fun bindLoginRepository(impl: LoginRepositoryImpl): LoginRepository

    @Binds
    @Singleton
    fun bindRegisterRepository(impl: RegisterRepositoryImpl): RegisterRepository

}

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providePreferenceDataStoreDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> = PreferenceDataStoreFactory.create(produceFile = {
        context.preferencesDataStoreFile("tag_yj2k_preferences_store")
    })


    @Singleton
    @Provides
    fun provideSessionKeys(
        @ApplicationContext context: Context
    ): SessionKeys = SessionKeys(context)
}


@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    private val json = Json {
        this.ignoreUnknownKeys = true
    }

    @Provides
    fun provideBaseUrl(): String = "https://mobility.ibemscreative.in"


    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, @ApplicationContext context: Context): Retrofit =
        Retrofit.Builder().client(OkHttpClient.Builder().apply {
            addInterceptor(HttpClientInterceptor(context))
            cache(
                Cache(
                    directory = File(context.cacheDir, "http_cache"),
                    maxSize = 50L * 1024L * 1024L // 50 MiB
                )
            )
        }.build()).addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(baseUrl).build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}