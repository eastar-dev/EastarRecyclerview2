package dev.eastar.recyclerview

import android.os.Bundle
import android.view.ViewGroup
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

    class DemoAdapter : BindingListAdapter<Data, RecyclerviewDemoItemWithoutDatabindingBinding>(R.layout.recyclerview_demo_item_without_databinding, diffUtil) {
        override fun onBindViewHolder(holder: Holder<RecyclerviewDemoItemWithoutDatabindingBinding>, position: Int) {
            super.onBindViewHolder(holder, position)
            //very spacial code
        }

        override fun onBindViewHolder(binder: RecyclerviewDemoItemWithoutDatabindingBinding, data: Data) {
            super.onBindViewHolder(binder, data)
            binder.imageView.setImageUrl(data.icon)
            binder.textView.text = data.name
        }
    }
}
