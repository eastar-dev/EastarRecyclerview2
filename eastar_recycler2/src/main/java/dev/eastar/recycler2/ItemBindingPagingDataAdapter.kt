@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package dev.eastar.recycler2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

open class ItemBindingPagingDataAdapter<DATA : Any, BIND : ViewDataBinding>(
    private val layoutResId: Int,
    itemCallback: DiffUtil.ItemCallback<DATA>,
    private val brId: Int = NoBrId
) : PagingDataAdapter<DATA, ItemBindingPagingDataAdapter.Holder<BIND>>(itemCallback) {

    companion object {
        const val NoBrId: Int = -1
    }

    @CallSuper
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder<BIND> {
        val itemView = getItemView(layoutResId, parent, viewType)
        val holder = getViewHolder(itemView, viewType)
        onCreateViewHolder(holder.itemBinding, viewType)
        return holder
    }

    open fun getItemView(@LayoutRes layoutResId: Int, parent: ViewGroup, viewType: Int): View {
        return LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
    }

    open fun getViewHolder(itemView: View, viewType: Int): Holder<BIND> {
        return Holder(itemView)
    }

    open fun onCreateViewHolder(binder: BIND, viewType: Int) {
    }

    @CallSuper
    override fun onBindViewHolder(holder: Holder<BIND>, position: Int) {
        if (brId > NoBrId) {
            holder.itemBinding.setVariable(brId, getItem(position))
            holder.itemBinding.executePendingBindings()
        }
        onBindViewHolder(holder.itemBinding, getItem(position))
    }

    open fun onBindViewHolder(binder: BIND, data: DATA?) {
    }

    class Holder<BIND : ViewDataBinding>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemBinding: BIND = DataBindingUtil.bind(itemView)!!
    }

    fun getItemAtPosition(position: Int): DATA? = getItem(position)
}
