package com.ramazanpeker.mvvmshoppinglist.ui.shoppinglist

import com.ramazanpeker.mvvmshoppinglist.data.db.entities.ShoppingItem

interface AddDialogListener {

    fun onAddButtonCliked(item:ShoppingItem)

}