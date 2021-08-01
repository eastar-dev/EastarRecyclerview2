@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package dev.eastar.recycler2

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil

open class DataBindingPagingDataAdapter<DATA : Any>(
    itemCallback: DiffUtil.ItemCallback<DATA>,
    defaultLayoutId: Int,
    defaultBrId: Int,
) : BindingPagingDataAdapter<DATA, ViewDataBinding>(
    itemCallback,
    defaultLayoutId,
    defaultBrId
)