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

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.mx.ddrtools.R
import org.jetbrains.annotations.NotNull

class DDRAlert {
    companion object{
        /**
         * Displays AlertDialog with custom title and message, includes 'acceptButton' as default with listener. Only add negative button when 'cancelTitle' is not null.
         * @param title String
         * @param message String
         * @param acceptTitle String
         * @param cancelTitle Int?
         * @param context Context
         * @param onAction (Boolean) -> Unit
         * */
        fun show(
            @NotNull title: String,
            @NotNull message: String,
            @NotNull context: Context,
            acceptTitle: Int = R.string.ddr_common_accept,
            cancelTitle: Int? = null,
            onAction: ((Boolean) -> Unit)? = null
        ) {
            AlertDialog.Builder(context).apply {
                setTitle(title)
                setMessage(message)
                setCancelable(false)
                setPositiveButton(acceptTitle){_,_ ->
                    onAction?.let {
                        it(true)
                    }
                }
                cancelTitle?.let {
                    setNegativeButton(cancelTitle){_,_ ->
                        onAction?.let {
                            it(false)
                        }
                    }
                }
            }.show()
        }
    }
}