package io.github.theuzfaleiro.trendingongithub

import android.app.Activity
import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.github.theuzfaleiro.trendingongithub.di.component.DaggerAppComponent
import javax.inject.Inject


open class TrendingOnGitHubApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingInjector: DispatchingAndroidInjector<Activity>


    override fun onCreate() {
        super.onCreate()

        setupDagger()
    }

    private fun setupDagger() {
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }

    override fun activityInjector() = activityDispatchingInjector


    open fun getBaseUrl() = BuildConfig.BASE_URL

}