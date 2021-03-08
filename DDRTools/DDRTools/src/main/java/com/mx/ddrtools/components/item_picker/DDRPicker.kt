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

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.StyleRes
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.mx.ddrtools.R
import com.mx.ddrtools.databinding.DdrPickerDialogBinding
import com.mx.ddrtools.model.DDRItemPicker
import com.mx.ddrtools.utils.DDRView

class DDRPicker(
    private val items: ArrayList<DDRItemPicker>,
    private val multiChoice: Boolean = false,
    private val title: String? = null,
    private val cancelable: Boolean = false,
    @ColorRes private val tintColor: Int = R.color.black,
    @StyleRes private val acceptButtonStyle: Int? = null,
    @StyleRes private val cancelButtonStyle: Int? = null,
    @StyleRes private val itemsStyle: Int? = null,
    @StyleRes private val titleStyle: Int? = null,
    private val onPicker: (Boolean) -> Unit
) : DialogFragment() {

    private lateinit var bindingView: DdrPickerDialogBinding
    private var closeByAccept: Boolean = false

    /** @constructor onCreateView
     * Inflate current view for this Fragment. Init all UI elements and retrieve 'arguments'
     * */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // inflate view for this fragment
        bindingView = DdrPickerDialogBinding.inflate(
            LayoutInflater.from(requireContext()),
            container,
            false
        )
        // load UI elements
        loadUIElements()
        // load items
        loadItems()
        return bindingView.root
    }

    /** This method load all properties elements in XML for this fragment */
    private fun loadUIElements() = with(bindingView) {
        isCancelable = cancelable
        // Configure title
        title?.let {
            // configure text view
            tvPickerTitle.text = it
            titleStyle?.let { style ->
                DDRView.setTextViewStyle(tvPickerTitle, style)
            }
            return@let
        } ?: run {
            tvPickerTitle.visibility = View.GONE
        }
        // Configure RecyclerView
        mainRVPicker.apply {
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            layoutManager = LinearLayoutManager(context)
        }
        // Configure CANCEL BUTTON
        cancelButtonStyle?.let { style ->
            DDRView.setButtonStyle(btnCancel, requireContext(), style)
        } ?: run {
            btnCancel.setText(R.string.ddr_common_cancel)
        }
        btnCancel.setOnClickListener {
            closeByAccept = false
            dismiss()
        }
        // Configure ACCEPT BUTTON
        acceptButtonStyle?.let { style ->
            DDRView.setButtonStyle(btnAccept, requireContext(), style)
        } ?: run {
            btnAccept.setText(R.string.ddr_common_accept)
        }
        btnAccept.setOnClickListener {
            closeByAccept = true
            dismiss()
        }
    }

    /** This method initialize adapter for add to recycler */
    private fun loadItems() = with(bindingView) {
        mainRVPicker.adapter = DDRPickerAdapter(
            items = items,
            multiChoice = multiChoice,
            itemStyle = itemsStyle,
            tintColor = tintColor
        ) {
            // evaluates if "Accept button" it can be visible
            someOptionIsSelected()
        }
    }

    private fun someOptionIsSelected() = with(bindingView) {
        items.forEach {
            if (it.selected) {
                btnAccept.visibility = View.VISIBLE
                return
            }
        }
        btnAccept.visibility = View.INVISIBLE
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        // lambda invoke
        onPicker(closeByAccept)
    }
}