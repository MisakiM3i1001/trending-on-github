package io.github.theuzfaleiro.trendingongithub.data.network

import dagger.Module
import dagger.Provides
import io.github.theuzfaleiro.trendingongithub.BuildConfig
import io.github.theuzfaleiro.trendingongithub.TrendingOnGitHubApplication
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class RetrofitConfigModule {

    @Provides
    @Singleton
    fun providesGitHubApi(okHttpClient: OkHttpClient, moshiConverterFactory: MoshiConverterFactory,
                          trendingOnGitHubApplication: TrendingOnGitHubApplication,
                          rxJava2CallAdapterFactory: RxJava2CallAdapterFactory): Retrofit =
            Retrofit.Builder()
                    .client(okHttpClient)
                    .addConverterFactory(moshiConverterFactory)
                    .addCallAdapterFactory(rxJava2CallAdapterFactory)
                    .baseUrl(trendingOnGitHubApplication.getBaseUrl())
                    .build()

    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    fun providesMoshiConverterFactory(): MoshiConverterFactory = MoshiConverterFactory.create()

    @Provides
    fun providesRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    @Provides
    fun providesBuildType(): String = BuildConfig.BUILD_TYPE

    @Provides
    fun providesHttpLoggingInterceptor(buildType: String): HttpLoggingInterceptor {
        return if (buildType.contentEquals("DEBUG")) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }
    }

}