package io.github.theuzfaleiro.trendingongithub.ui.feature.common

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity<out P : BasePresenter<BaseView>> : BaseView, AppCompatActivity() {
    private lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = instantiatePresenter()
    }

    /**
     * Instantiates the presenter the Activity is based on.
     */
    protected abstract fun instantiatePresenter(): P


}