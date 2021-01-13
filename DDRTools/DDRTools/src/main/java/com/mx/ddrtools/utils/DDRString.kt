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
package com.mx.ddrtools.utils

import android.text.TextUtils
import java.util.*
import java.util.regex.Pattern

/**
 *
 * This class is an String helper. All methods are public an companion
 *
 */
class DDRString {
    companion object {
        /**
         *
         * This function concat all string elements in Array.
         *
         * @param elements array of strings
         * @return formed string
         */
        fun joinElements(elements: Array<String>?): String {
            elements?.let {
                return it.joinToString()
            }
            return ""
        }

        /**
         *
         * This function concat all string elements in Array with specific separator.
         *
         * @param elements array of strings
         * @return formed string
         */
        fun joinElements(elements: Array<String>?, separator: String): String {
            elements?.let {
                return it.joinToString(separator)
            }
            return ""
        }

        /**
         *
         * This function concat all string elements in Array with specific separator.
         *
         * @param elements array of strings
         * @return formed string
         */
        fun joinElements(elements: Array<String>?, title: String, separator: String): String {
            var buildString = ""
            elements?.let {
                it.forEachIndexed { idx, item ->
                    buildString = if ((idx + 1) == elements.size) {
                        "$buildString$title $item"
                    } else {
                        "$buildString$title $item$separator"
                    }
                }
            }
            return buildString
        }

        /**
         *
         * This function generates new string with capitalized all words. Need indicates current separator
         *
         * @param text string
         * @return capitalized string
         */
        fun capitalizeByWords(text: String, separator: String): String {
            return text
                .split(separator)
                .joinToString(separator) { it.capitalize(Locale.getDefault()) }
                .trimEnd()
        }

        /**
         *
         * This function evaluates email format
         *
         * @param email string
         * @return boolean email is valid
         */
        fun isEmailValid(email: String): Boolean {
            return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches()
        }

        /**
         *
         * This function to convert String to int. Start in specific index
         *
         * @param idx Int index to start cut
         * @param text String
         * @return Int
         * */
        fun stringToInt(idx:Int? = 0, text: String): Int {
            val cutString = if (idx!! > 0) text.substring(idx) else text
            return cutString.toInt()
        }

        /**
         *
         * This function to evaluates RFC (10 and 13 chars).
         *
         * @param rfc String
         * @return boolean rfc is valid
         */
        fun isRFCValid(rfc: String): Boolean {
            return when{
                TextUtils.isEmpty(rfc) -> false
                else -> {
                    if (rfc.length == 10) {
                        rfc.matches("^([A-Z][AEIOUX][A-Z]{2}\\d{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[12]\\d|3[01]))?$".toRegex())
                    } else {
                        rfc.matches("^([A-ZÑ\\x26]{3,4}([0-9]{2})(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])([A-Z]|[0-9]){2}([A]|[0-9]){1})?$".toRegex())
                    }
                }
            }
        }
    }
}