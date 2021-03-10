/**
Copyright 2021 DDRTools

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 * */
package com.mx.ddrtools.components.item_picker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.StyleRes
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.paris.extensions.style
import com.mx.ddrtools.databinding.DdrPickerItemBinding
import com.mx.ddrtools.model.DDRItemPicker
import com.mx.ddrtools.utils.DDRView

class DDRPickerAdapter(
    private val items: ArrayList<DDRItemPicker>,
    private val multiChoice: Boolean = false,
    @StyleRes private val itemStyle: Int? = null,
    @ColorRes private val tintColor: Int? = null,
    private val onSelection: () -> Unit
) : RecyclerView.Adapter<DDRPickerAdapter.ViewHolder>() {

    private var selectedItem: DDRItemPicker? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) = ViewHolder(
        DdrPickerItemBinding.inflate(LayoutInflater.from(p0.context), p0, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], itemStyle, tintColor, onSelection)
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(private val binding: DdrPickerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DDRItemPicker, style: Int?, tintColor: Int?, onSelection: () -> Unit) =
            with(binding) {
                // Set item name
                tvName.text = item.name
                // Set style
                style?.let {
                    // change style for label
                    tvName.style(it)
                }
                // Set tint color of checkbox
                tintColor?.let {
                    // change style for label
                    DDRView.setCheckBoxTint(cbItem, cbItem.context, it)
                }
                cbItem.isChecked = item.selected
                cbItem.setOnClickListener {
                    item.selected = cbItem.isChecked
                    if (cbItem.isChecked) {
                        if (!multiChoice) {
                            selectedItem?.let {
                                it.selected = false
                                return@let
                            }
                            selectedItem = item
                        }
                    } else {
                        if (!multiChoice) {
                            selectedItem = null
                        }
                    }
                    onSelection()
                    notifyDataSetChanged()
                }
            }
    }
}