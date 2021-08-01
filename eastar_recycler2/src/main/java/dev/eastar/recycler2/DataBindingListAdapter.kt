@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package dev.eastar.recycler2

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil

open class DataBindingListAdapter<DATA>(
    itemCallback: DiffUtil.ItemCallback<DATA>,
) : BindingListAdapter<DATA, ViewDataBinding>(itemCallback)