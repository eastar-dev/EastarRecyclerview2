@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package dev.eastar.recycler2

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil

open class DataBindingPagingDataAdapter<DATA : Any>(
    itemCallback: DiffUtil.ItemCallback<DATA>,
) : BindingPagingDataAdapter<DATA, ViewDataBinding>(itemCallback)