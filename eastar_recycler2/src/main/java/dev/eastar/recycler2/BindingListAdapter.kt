@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package dev.eastar.recycler2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

open class BindingListAdapter<DATA, BIND : ViewDataBinding>(
    itemCallback: DiffUtil.ItemCallback<DATA>,
    private val layoutResId: Int = NoResId,
    private val brId: Int = NoBrId
) : ListAdapter<DATA, BindingListAdapter.Holder<BIND>>(itemCallback) {

    companion object {
        const val NoBrId: Int = -1
        const val NoResId: Int = -1
    }

    @CallSuper
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder<BIND> {
        val itemView = getItemView(parent, viewType)
        val holder = getViewHolder(itemView, viewType)
        onCreateViewHolder(holder.itemBinding, viewType)
        return holder
    }

    open fun getItemView(parent: ViewGroup, viewType: Int): View {
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

    open fun onBindViewHolder(binder: BIND, data: DATA) {
    }

    class Holder<BIND : ViewDataBinding>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemBinding: BIND = DataBindingUtil.bind(itemView)!!
    }

    fun getItemAtPosition(position: Int): DATA = getItem(position)
}
