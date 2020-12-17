package com.ramazanpeker.mvvmshoppinglist.ui.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ramazanpeker.mvvmshoppinglist.R
import com.ramazanpeker.mvvmshoppinglist.data.db.ShoppingDatabase
import com.ramazanpeker.mvvmshoppinglist.data.db.entities.ShoppingItem
import com.ramazanpeker.mvvmshoppinglist.data.repositories.ShoppingRepository
import com.ramazanpeker.mvvmshoppinglist.other.ShoppingItemAdapter
import kotlinx.android.synthetic.main.activity_shopping.*

class ShoppingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val database=ShoppingDatabase(this)
        val repository=ShoppingRepository(database)
        val factory=ShoppingViewModelFactory(repository)
        val viewModel=ViewModelProviders.of(this,factory).get(ShoppingViewModel::class.java)

        val adapter=ShoppingItemAdapter(listOf(),viewModel)
        rvShoppingItems.layoutManager=LinearLayoutManager(this)
        rvShoppingItems.adapter=adapter

        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items=it
            adapter.notifyDataSetChanged()
        })

        fab.setOnClickListener {
            AddShoppingItemDialog(this,
            object:AddDialogListener{
                override fun onAddButtonCliked(item: ShoppingItem) {

                    viewModel.upsert(item)
                }

            }).show()
        }
    }
}