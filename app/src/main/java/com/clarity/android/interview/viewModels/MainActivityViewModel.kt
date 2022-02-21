package com.clarity.android.interview.viewModels

import com.clarity.android.interview.ItemListViewState
import com.clarity.android.interview.ItemRow
import io.reactivex.annotations.Nullable

class MainActivityViewModel {

  interface UpdateListener {
    fun onUpdate(state: ItemListViewState)
  }

  private var itemListViewState: ItemListViewState
  private var listener: UpdateListener? = null

  init {
    val items = listOf(
        ItemRow("Cabbage"),
        ItemRow("Apple"),
        ItemRow("Bread")
    )

    itemListViewState = ItemListViewState("Delivery Items", items)
  }

  fun setStateUpdateListener(@Nullable listener: UpdateListener?) {
    this.listener = listener

    listener?.onUpdate(itemListViewState)
  }
}
