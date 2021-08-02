@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package dev.eastar.recycler2

import android.view.LayoutInflater
import android.view.View
import android.view.View.NO_ID
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

open class BindingPagingDataAdapter<DATA : Any, BIND : ViewDataBinding>(
    @NonNull itemCallback: DiffUtil.ItemCallback<DATA>,
    private val defaultLayoutId: Int = NO_ID,
    private val defaultBrId: Int = NO_ID,
) : PagingDataAdapter<DATA, Holder<BIND>>(itemCallback) {

    override fun getItemViewType(position: Int): Int =
        (getItem(position) as? ViewType)?.getViewType() ?: super.getItemViewType(position)

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

    open fun onBindViewHolder(itemBinding: BIND, data: DATA?) {}

    @CallSuper
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder<BIND> {
        val itemView = getView(parent, viewType)
        val holder = getViewHolder(itemView, viewType)
        onCreateViewHolder(holder.itemBinding, viewType)
        return holder
    }

    open fun onCreateViewHolder(itemBinding: BIND, viewType: Int) {}

    open fun getView(parent: ViewGroup, viewType: Int): View {
        val layoutResId = getLayout(viewType)
        return LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
    }

    @LayoutRes
    open fun getLayout(viewType: Int): Int = defaultLayoutId

    open fun getViewHolder(itemView: View, viewType: Int): Holder<BIND> {
        val brId = getBrId(viewType)
        return Holder(itemView, brId)
    }

    open fun getBrId(viewType: Int): Int = defaultBrId
}
