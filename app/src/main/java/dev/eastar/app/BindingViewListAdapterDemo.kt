package dev.eastar.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.eastar.recycler2.BindingListAdapter
import dev.eastar.recyclerview.databinding.RecyclerviewDemoBinding
import dev.eastar.recyclerview.databinding.RecyclerviewDemoItemWithoutDatabindingBinding
import dev.eastar.recyclerview.model.*

class BindingViewListAdapterDemo : AppCompatActivity() {
    private lateinit var bb: RecyclerviewDemoBinding
    private val items = DATA_SOURCE.mapIndexed { index, text -> Data("$ICON$index", text) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bb = RecyclerviewDemoBinding.inflate(layoutInflater)
        setContentView(bb.root)
        val adapter = DemoAdapter()
        bb.list.adapter = adapter
        adapter.submitList(items)
    }

    class DemoAdapter : BindingListAdapter<Data, RecyclerviewDemoItemWithoutDatabindingBinding>(diffUtil, R.layout.recyclerview_demo_item_without_databinding) {
        override fun onBindViewHolder(itemBinding: RecyclerviewDemoItemWithoutDatabindingBinding, data: Data) {
            super.onBindViewHolder(itemBinding, data)
            itemBinding.imageView.setImageUrl(data.icon)
            itemBinding.textView.text = data.name
        }
    }
}
