package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.LightHistoryItem
import com.example.myapplication.web.WebClient
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.android.synthetic.main.item_history.view.*
import kotlinx.coroutines.launch

class HistoryFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = Adapter()
        historyView.layoutManager = LinearLayoutManager(requireContext())
        historyView.adapter = adapter

        lifecycleScope.launch {
            val history = WebClient.getHistory()
            adapter.setNewList(history)
        }
    }

    class Adapter: RecyclerView.Adapter<Adapter.ViewHolder>() {

        private val list = mutableListOf<LightHistoryItem>()

        fun setNewList(newList: List<LightHistoryItem>) {
            list.clear()
            list.addAll(newList)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_history, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(list[position])
        }

        class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {

            fun bind(item: LightHistoryItem) {
                containerView.date.text = "Дата: ${item.date}"
                containerView.light.text = "Освещенность ${item.light}"
            }
        }
    }
}