package io.github.theuzfaleiro.trendingongithub.utils

import io.reactivex.Scheduler

interface RxSchedulers {

    fun io(): Scheduler

    fun ui(): Scheduler
}
