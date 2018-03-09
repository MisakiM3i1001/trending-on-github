package io.github.theuzfaleiro.trendingongithub.di.component

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.github.theuzfaleiro.trendingongithub.TrendingOnGitHubApplication
import io.github.theuzfaleiro.trendingongithub.data.network.RetrofitConfigModule
import io.github.theuzfaleiro.trendingongithub.di.builder.ActivityBuilder
import io.github.theuzfaleiro.trendingongithub.di.module.AppModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class), (AppModule::class), (RetrofitConfigModule::class), (ActivityBuilder::class)])
internal interface AppComponent : AndroidInjector<TrendingOnGitHubApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<TrendingOnGitHubApplication>()
}