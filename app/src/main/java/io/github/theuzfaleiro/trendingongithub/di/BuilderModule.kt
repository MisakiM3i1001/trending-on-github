package io.github.theuzfaleiro.trendingongithub.di

import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector
import io.github.theuzfaleiro.trendingongithub.di.module.RepositoryModule
import io.github.theuzfaleiro.trendingongithub.di.scope.PerActivity
import io.github.theuzfaleiro.trendingongithub.ui.feature.repository.RepositoryActivity


@Module(includes = [AndroidInjectionModule::class])
abstract class BuilderModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [(RepositoryModule::class)])
    abstract fun homeActivityInjector(): RepositoryActivity
}