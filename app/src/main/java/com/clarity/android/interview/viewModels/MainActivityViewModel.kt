package com.clarity.android.interview.viewModels

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.clarity.android.interview.ItemListViewState
import com.clarity.android.interview.ItemRow
import com.clarity.android.interview.R
import com.clarity.android.interview.network.NetworkApi
import com.clarity.android.interview.network.NetworkService
import com.clarity.android.interview.network.OrderResponse
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.Nullable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel {

  //An interface is an abstract class that is used to group related methods with empty
  // bodies and they only have methods inside it implemented by a class to inherit.
  interface UpdateListener {
    fun onUpdate(state: ItemListViewState){
    }
  }
  private var itemListViewState: ItemListViewState
  private var listener: UpdateListener? = null
  var ordersList: MutableLiveData<OrderResponse> = MutableLiveData()

  fun getBookListObserver(): MutableLiveData<OrderResponse> {
    return ordersList
  }

  init {
    val items = listOf(
      ItemRow(R.string.orders.toString()),
      ItemRow(ordersList.value?.items.toString())
    )
    itemListViewState = ItemListViewState("Delivery Items", items)
  }

  fun setStateUpdateListener(@Nullable listener: UpdateListener?) {
    this.listener = listener
    listener?.onUpdate(itemListViewState)
  }

  fun makeApiCall(id: Long) {
    val retroInstance  = NetworkService.getRetroInstance().create(NetworkApi::class.java)
    retroInstance.fetchOrderByIdObservable(id)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(getBookListObserverRx())
  }

  private fun getBookListObserverRx(): Observer<OrderResponse> {
    return object : Observer<OrderResponse> {
      override fun onComplete() {
        //hide progress indicator .
      }

      override fun onError(e: Throwable) {
        ordersList.postValue(null)
      }

      override fun onNext(t: OrderResponse) {
        ordersList.postValue(t)
        Log.d(ContentValues.TAG, "onSubscribe : $t" )
      }

      override fun onSubscribe(d: Disposable) {
        //start showing progress indicator.
      }
    }
  }

}