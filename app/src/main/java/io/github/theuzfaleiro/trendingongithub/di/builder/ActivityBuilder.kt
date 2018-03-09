package io.github.theuzfaleiro.trendingongithub.di.builder

import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector
import io.github.theuzfaleiro.trendingongithub.di.scope.PerActivity
import io.github.theuzfaleiro.trendingongithub.ui.feature.repository.RepositoryActivity
import io.github.theuzfaleiro.trendingongithub.ui.feature.repository.di.RepositoryModule

@Module(includes = [(AndroidInjectionModule::class)])
internal abstract class ActivityBuilder {

    @PerActivity
    @ContributesAndroidInjector(modules = [(RepositoryModule::class)])
    internal abstract fun homeActivityInjector(): RepositoryActivity
}
