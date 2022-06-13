package com.clarity.android.interview

data class ItemListViewState(
  val toolbarTitle: String,
  var items: List<ItemRow>
)

data class ItemRow(val name: String)