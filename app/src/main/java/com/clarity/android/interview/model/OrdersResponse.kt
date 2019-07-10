package com.clarity.android.interview.model

import com.google.gson.annotations.SerializedName

class OrdersResponse(
  @SerializedName("orders") val orders: List<Long>
)
