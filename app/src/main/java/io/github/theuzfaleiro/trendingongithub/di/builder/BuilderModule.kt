package io.github.theuzfaleiro.trendingongithub.di.builder

import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector
import io.github.theuzfaleiro.trendingongithub.ui.feature.repository.di.RepositoryModule
import io.github.theuzfaleiro.trendingongithub.di.scope.PerActivity
import io.github.theuzfaleiro.trendingongithub.ui.feature.repository.RepositoryActivity

@Module
abstract class BuilderModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [(RepositoryModule::class)])
    abstract fun bindsRepositoryActivity(): RepositoryActivity
}