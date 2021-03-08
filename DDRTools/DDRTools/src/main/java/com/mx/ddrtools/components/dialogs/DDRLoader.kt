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
package com.mx.ddrtools.components.dialogs

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.annotation.ColorRes
import androidx.fragment.app.DialogFragment
import com.mx.ddrtools.R
import com.mx.ddrtools.databinding.DdrLoaderBinding

class DDRLoader(@ColorRes private val loaderColor:Int? = R.color.white) : DialogFragment(){

    private lateinit var binding : DdrLoaderBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding = DdrLoaderBinding.inflate(
            LayoutInflater.from(requireContext()),
            container,
            false
        )
        binding.rotate.setLoaderColor(loaderColor!!)
        binding.rotate.start()
        return binding.root
    }
}