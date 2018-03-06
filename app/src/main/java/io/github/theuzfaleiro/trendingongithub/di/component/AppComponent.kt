package io.github.theuzfaleiro.trendingongithub.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import io.github.theuzfaleiro.trendingongithub.TrendingOnGitHubApplication
import io.github.theuzfaleiro.trendingongithub.data.network.RetrofitConfigModule
import io.github.theuzfaleiro.trendingongithub.di.builder.BuilderModule
import io.github.theuzfaleiro.trendingongithub.di.module.AppModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidInjectionModule::class), (AppModule::class), (RetrofitConfigModule::class), (BuilderModule::class)])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: TrendingOnGitHubApplication)
}