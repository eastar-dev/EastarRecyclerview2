@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package dev.eastar.recycler2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

open class ItemBindingListAdapter<DATA, BIND : ViewDataBinding>(
    private val layoutResId: Int,
    itemCallback: DiffUtil.ItemCallback<DATA>,
    private val brId: Int = NoBrId
) : ListAdapter<DATA, ItemBindingListAdapter.Holder<BIND>>(itemCallback) {

    companion object {
        const val NoBrId: Int = -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder<BIND> {
        val itemView = getItemView(layoutResId, parent, viewType)
        return Holder(itemView)
    }

    open fun getItemView(@LayoutRes layer: Int, parent: ViewGroup, viewType: Int): View {
        return LayoutInflater.from(parent.context).inflate(layer, parent, false)
    }

    @CallSuper
    override fun onBindViewHolder(holder: Holder<BIND>, position: Int) {
        if (brId > NoBrId) {
            holder.itemBinding.setVariable(brId, getItem(position))
            holder.itemBinding.executePendingBindings()
        }
    }

    class Holder<BIND : ViewDataBinding>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemBinding: BIND = DataBindingUtil.bind(itemView)!!
    }
}
