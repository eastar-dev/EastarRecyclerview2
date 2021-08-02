package dev.eastar.recycler2

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class Holder<BIND : ViewDataBinding>(itemView: View, val brId: Int) : RecyclerView.ViewHolder(itemView) {
    var itemBinding: BIND = DataBindingUtil.bind(itemView)!!
}