@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package dev.eastar.recycler2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

open class BindingPagingDataAdapter<DATA : Any, BIND : ViewDataBinding>(
    @NonNull itemCallback: DiffUtil.ItemCallback<DATA>,
    private val defaultLayoutId: Int = NO_ID,
    private val defaultBrId: Int = NO_ID
) : PagingDataAdapter<DATA, BindingPagingDataAdapter.Holder<BIND>>(itemCallback) {

    @CallSuper
    override fun onBindViewHolder(holder: Holder<BIND>, position: Int) {
        onBindData(holder, position)
        onBindViewHolder(holder.itemBinding, getItem(position))
    }

    private fun onBindData(holder: Holder<BIND>, position: Int) {
        if (holder.brId > NO_ID) {
            holder.itemBinding.setVariable(holder.brId, getBindingItem(getItem(position)))
            holder.itemBinding.executePendingBindings()
        }
    }

    open fun getBindingItem(data: DATA?): Any? = data

    open fun onBindViewHolder(binder: BIND, data: DATA?) {}

    @CallSuper
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder<BIND> {
        val itemView = getItemView(parent, viewType)
        val holder = getViewHolder(itemView, viewType)
        onCreateViewHolder(holder.itemBinding, viewType)
        return holder
    }

    open fun getItemView(parent: ViewGroup, viewType: Int): View {
        @LayoutRes val layoutResId = getItemLayoutId(viewType)
        return LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
    }

    @LayoutRes
    open fun getItemLayoutId(viewType: Int): Int = defaultLayoutId

    open fun getViewHolder(itemView: View, viewType: Int): Holder<BIND> {
        @LayoutRes val brId = getHolderBrId(viewType)
        return Holder(itemView, brId)
    }

    open fun getHolderBrId(viewType: Int): Int = defaultBrId

    open fun onCreateViewHolder(binder: BIND, viewType: Int) {
    }

    class Holder<BIND : ViewDataBinding>(itemView: View, val brId: Int) : RecyclerView.ViewHolder(itemView) {
        var itemBinding: BIND = DataBindingUtil.bind(itemView)!!
    }

    companion object {
        const val NO_ID: Int = -1
    }
}
