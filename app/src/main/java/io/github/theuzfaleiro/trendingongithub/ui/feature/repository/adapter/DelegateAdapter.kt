package io.github.theuzfaleiro.trendingongithub.ui.feature.repository.adapter

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import io.github.theuzfaleiro.trendingongithub.data.model.repository.Repository
import io.github.theuzfaleiro.trendingongithub.ui.feature.common.adapter.AdapterConstants
import io.github.theuzfaleiro.trendingongithub.ui.feature.common.adapter.ViewType
import io.github.theuzfaleiro.trendingongithub.ui.feature.common.adapter.ViewTypeAdapter

class DelegateAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<ViewType>
    private var delegateAdapters = SparseArrayCompat<ViewTypeAdapter>()
    private val loadingItem = object : ViewType {
        override fun getViewType() = AdapterConstants.LOADING
    }

    init {
        delegateAdapters.put(AdapterConstants.LOADING, LoadingAdapter())
        delegateAdapters.put(AdapterConstants.REPOSITORY, RepositoryAdapter {

        })

        items = ArrayList()
        items.add(loadingItem)

    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            delegateAdapters.get(viewType).onCreateViewHolder(parent)


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder, items[position])
    }

    override fun getItemViewType(position: Int) = items[position].getViewType()

    fun addNews(news: List<Repository>) {
        // first remove loading and notify
        val initPosition = items.size - 1
        items.removeAt(initPosition)
        notifyItemRemoved(initPosition)

        // insert news and the loading at the end of the list
        items.addAll(news)
        items.add(loadingItem)
        notifyItemRangeChanged(initPosition, items.size + 1 /* plus loading item */)
    }

    fun clearAndAddNews(news: List<Repository>) {
        items.clear()
        notifyItemRangeRemoved(0, getLastPosition())

        items.addAll(news)
        items.add(loadingItem)
        notifyItemRangeInserted(0, items.size)
    }

    fun getNews(): List<Repository> =
            items
                    .filter { it.getViewType() == AdapterConstants.REPOSITORY }
                    .map { it as Repository }


    private fun getLastPosition() = if (items.lastIndex == -1) 0 else items.lastIndex

}