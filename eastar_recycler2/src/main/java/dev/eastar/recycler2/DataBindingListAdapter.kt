@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package dev.eastar.recycler2

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil

open class DataBindingListAdapter<DATA>(
    private val layoutResId: Int,
    itemCallback: DiffUtil.ItemCallback<DATA>,
    private val brId: Int
) : BindingListAdapter<DATA, ViewDataBinding>(itemCallback, layoutResId, brId)