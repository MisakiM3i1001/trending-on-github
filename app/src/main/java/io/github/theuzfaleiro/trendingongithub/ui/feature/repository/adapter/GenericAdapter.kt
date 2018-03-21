package io.github.theuzfaleiro.trendingongithub.ui.feature.repository.adapter

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import io.github.theuzfaleiro.trendingongithub.data.model.repository.Repository
import io.github.theuzfaleiro.trendingongithub.ui.feature.common.adapter.AdapterConstants
import io.github.theuzfaleiro.trendingongithub.ui.feature.common.adapter.ViewType
import io.github.theuzfaleiro.trendingongithub.ui.feature.common.adapter.ViewTypeAdapter

class GenericAdapter(clickListener: (repository: Repository) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var viewTypeItems: ArrayList<ViewType>

    private var genericAdapters = SparseArrayCompat<ViewTypeAdapter>()

    private val loadingItem = object : ViewType {
        override fun getViewType() = AdapterConstants.LOADING
    }

    init {
        genericAdapters.put(AdapterConstants.LOADING, LoadingAdapter())
        genericAdapters.put(AdapterConstants.REPOSITORY, RepositoryAdapter(clickListener))

        viewTypeItems = ArrayList()
        viewTypeItems.add(loadingItem)
    }

    override fun getItemCount(): Int = viewTypeItems.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            genericAdapters.get(viewType).onCreateViewHolder(parent)


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        genericAdapters.get(getItemViewType(position)).onBindViewHolder(holder, viewTypeItems[position])
    }

    override fun getItemViewType(position: Int) = viewTypeItems[position].getViewType()

    fun addNews(news: List<Repository>) {

        val initialPosition = viewTypeItems.size - 1

        viewTypeItems.removeAt(initialPosition)
        notifyItemRemoved(initialPosition)

        viewTypeItems.addAll(news)

        viewTypeItems.add(loadingItem)

        notifyItemRangeChanged(initialPosition, viewTypeItems.size + 1)
    }
}