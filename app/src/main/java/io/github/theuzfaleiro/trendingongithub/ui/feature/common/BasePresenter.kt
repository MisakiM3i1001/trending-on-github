package io.github.theuzfaleiro.trendingongithub.ui.feature.common

abstract class BasePresenter<out V : BaseView>(protected val view: V) {

    init {
        inject()
    }

    /**
     * This method may be called when the presenter view is created
     */
    open fun onViewCreated(){}

    /**
     * This method may be called when the presenter view is destroyed
     */
    open fun onViewDestroyed(){}

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        //TODO: Implement this method
    }
}