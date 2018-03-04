package io.github.theuzfaleiro.trendingongithub.di

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Module(includes = [AndroidInjectionModule::class])
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindsApplication(app: Application): Application
}