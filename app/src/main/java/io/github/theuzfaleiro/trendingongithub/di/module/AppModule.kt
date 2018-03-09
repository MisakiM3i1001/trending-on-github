package io.github.theuzfaleiro.trendingongithub.di.module

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import io.github.theuzfaleiro.trendingongithub.utils.AppScheduler
import io.github.theuzfaleiro.trendingongithub.utils.RxSchedulers
import javax.inject.Singleton

@Module(includes = [(AndroidInjectionModule::class)])
internal abstract class AppModule {

    @Binds
    @Singleton
    internal abstract fun application(app: Application): Application
}