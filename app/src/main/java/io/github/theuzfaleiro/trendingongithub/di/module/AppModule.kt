package io.github.theuzfaleiro.trendingongithub.di.module

import android.app.Application
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindsApplication(app: Application): Application
}