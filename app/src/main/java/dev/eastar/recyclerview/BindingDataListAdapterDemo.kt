package dev.eastar.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.eastar.recycler2.DataBindingListAdapter
import dev.eastar.recyclerview.databinding.RecyclerviewDemoBinding
import dev.eastar.recyclerview.model.DATA_SOURCE
import dev.eastar.recyclerview.model.Data
import dev.eastar.recyclerview.model.ICON
import dev.eastar.recyclerview.model.diffUtil

class BindingDataListAdapterDemo : AppCompatActivity() {
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

    class DemoAdapter : DataBindingListAdapter<Data>(R.layout.recyclerview_demo_item, diffUtil, BR.data)
    //same to
    //class DemoAdapter : BindingListAdapter<Data, ViewDataBinding>(R.layout.recyclerview_demo_item, diffUtil, BR.data)
}
