@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package dev.eastar.recycler2

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil

open class DataBindingListAdapter<DATA>(
    itemCallback: DiffUtil.ItemCallback<DATA>,
    layoutResId: Int = NoResId,
    brId: Int = NoBrId
) : BindingListAdapter<DATA, ViewDataBinding>(itemCallback, layoutResId, brId)