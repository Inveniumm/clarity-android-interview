package com.clarity.android.interview

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.clarity.android.interview.network.DeliveryItem
import com.clarity.android.interview.viewModels.MainActivityViewModel
import com.clarity.android.interview.viewModels.MainActivityViewModel.UpdateListener
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

  private lateinit var toolbar: Toolbar
  private lateinit var recyclerView: RecyclerView
  private lateinit var adapter: ItemAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val viewModel = MainActivityViewModel()
    val itemScreenContainerView = findViewById<View>(R.id.item_screen_container)
    bindViews(itemScreenContainerView)

    viewModel.setStateUpdateListener(object : UpdateListener {
      override fun onUpdate(state: ItemListViewState) {
        renderItemList(state)
      }
    })

  }

  private fun renderItemList(state: ItemListViewState) {
    val viewModel = MainActivityViewModel()
    viewModel.makeApiCall(1)

    viewModel.getBookListObserver().observe(this) {
      if (it != null) {
        toolbarText.text = state.toolbarTitle
        // as operator which casts the object to another object with particular reference.
        adapter.items = it.items as ArrayList<DeliveryItem>
        //update adapter...
        adapter.notifyDataSetChanged()
      } else {
        ///if the response is null then it will send a toast to show that it is broken.
        Toast.makeText(this, "Error in fetching data", Toast.LENGTH_SHORT).show()
      }
    }
  }

  private fun bindViews(parent: View) {
    toolbar = parent.findViewById(R.id.toolbar)

    recyclerView = parent.findViewById(R.id.recycler_view)
    recyclerView.layoutManager = LinearLayoutManager(parent.context, RecyclerView.VERTICAL, false)
    adapter = ItemAdapter()
    recyclerView.adapter = adapter
  }
}
