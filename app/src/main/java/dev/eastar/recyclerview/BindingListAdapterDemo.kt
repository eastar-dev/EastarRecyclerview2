package dev.eastar.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.eastar.recycler2.BindingListAdapter
import dev.eastar.recyclerview.databinding.RecyclerviewDemoBinding
import dev.eastar.recyclerview.databinding.RecyclerviewDemoItemBinding
import dev.eastar.recyclerview.model.*

class BindingListAdapterDemo : AppCompatActivity() {
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

    class DemoAdapter : BindingListAdapter<Data, RecyclerviewDemoItemBinding>(R.layout.recyclerview_demo_item, diffUtil) {
        override fun onBindViewHolder(binder: RecyclerviewDemoItemBinding, data: Data) {
            super.onBindViewHolder(binder, data)
            binder.imageView.setImageUrl(data.icon)
        }
    }

}
