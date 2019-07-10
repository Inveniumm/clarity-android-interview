package com.clarity.android.interview

import com.clarity.android.interview.network.NetworkService
import io.reactivex.annotations.Nullable

class MainActivityViewModel {
  private val networkService: NetworkService

  private var itemListViewState: ItemListViewState
  private var listener: ((ItemListViewState) -> Unit)? = null

  init {
    networkService = NetworkService()
    itemListViewState = ItemListViewState("Delivery Items", emptyList())
  }

  fun setStateUpdateListener(@Nullable listener: ((ItemListViewState) -> Unit)?) {
    this.listener = listener
  }

  fun loadItems() {
    val items = listOf(
      ItemRow("Cabbage"),
      ItemRow("Apple"),
      ItemRow("Bread")
    )
    itemListViewState = itemListViewState.copy(items = items)
    listener?.invoke(itemListViewState)
  }
}
