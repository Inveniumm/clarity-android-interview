package com.clarity.android.interview.model

import com.google.gson.annotations.SerializedName

class OrderResponse(
  @SerializedName("items") val items: List<DeliveryItem>
)
