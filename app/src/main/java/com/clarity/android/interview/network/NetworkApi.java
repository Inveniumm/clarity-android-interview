package com.clarity.android.interview.network;

import com.clarity.android.interview.model.DiscountResponse;
import com.clarity.android.interview.model.OrderResponse;
import com.clarity.android.interview.model.OrdersResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NetworkApi {

  @GET("orders")
  Observable<OrdersResponse> fetchOrdersObservable();

  @GET("order/{order_id}")
  Observable<OrderResponse> fetchOrderByIdObservable(@Path("order_id") long id);

  @GET("discount/{item_id}")
  Observable<DiscountResponse> discountObservable(@Path("item_id") long id);
}
