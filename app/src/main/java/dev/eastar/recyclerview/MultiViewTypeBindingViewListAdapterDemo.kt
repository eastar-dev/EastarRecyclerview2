package dev.eastar.recyclerview

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import dev.eastar.recycler2.BindingListAdapter
import dev.eastar.recycler2.Holder
import dev.eastar.recyclerview.databinding.RecyclerviewDemoBinding
import dev.eastar.recyclerview.databinding.RecyclerviewDemoItemWithoutDatabindingBinding
import dev.eastar.recyclerview.model.*

class MultiViewTypeBindingViewListAdapterDemo : AppCompatActivity() {
    private lateinit var bb: RecyclerviewDemoBinding

    sealed class ListItem {
        data class StringItem(val title: String) : ListItem()
        data class DataItem(
            val icon: String,
            val name: String
        ) : ListItem()
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ListItem>() {
            override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem) = when {
                oldItem is ListItem.DataItem && newItem is ListItem.DataItem -> oldItem.name == newItem.name
                oldItem is ListItem.StringItem && newItem is ListItem.StringItem -> oldItem.title == newItem.title
                else -> false
            }

            override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem) = when {
                oldItem is ListItem.DataItem && newItem is ListItem.DataItem -> oldItem == newItem
                oldItem is ListItem.StringItem && newItem is ListItem.StringItem -> oldItem == newItem
                else -> false
            }
        }
    }

    private val items = DATA_SOURCE
        .mapIndexed { index, text -> Data("$ICON$index", text) }
        .sortedBy { it.name }
        .fold(mutableListOf<ListItem>()) { acc, data ->
            if ((acc.lastOrNull() as? ListItem.DataItem)?.name?.getOrNull(0) != data.name[0]) {
                acc.add(ListItem.StringItem(data.name[0].toString()))
            }
            acc.add(ListItem.DataItem(icon = data.icon, name = data.name))
            acc
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bb = RecyclerviewDemoBinding.inflate(layoutInflater)
        setContentView(bb.root)
        val adapter = DemoAdapter()
        bb.list.adapter = adapter
        adapter.submitList(items)
    }

    class DemoAdapter : BindingListAdapter<ListItem, ViewDataBinding>(diffUtil) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder<ViewDataBinding> {
            return super.onCreateViewHolder(parent, viewType)
        }

        override fun onBindViewHolder(holder: Holder<ViewDataBinding>, position: Int) {
            super.onBindViewHolder(holder, position)
        }

//        override fun onBindViewHolder(binder: RecyclerviewDemoItemWithoutDatabindingBinding, data: Data) {
//            super.onBindViewHolder(binder, data)
//            binder.imageView.setImageUrl(data.icon)
//            binder.textView.text = data.name
//        }
    }
}
