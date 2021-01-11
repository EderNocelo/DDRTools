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

class DDRString {
    companion object {
        fun concatElements(elements:Array<String>?) : String{
            var buildString = ""
            elements?.let {
                it.forEach { element ->
                    buildString += element
                }
            }
            return buildString
        }
        fun concatElements(elements:Array<String>?, separator:String) : String{
            var buildString = ""
            elements?.let {
                it.forEach { element ->
                    buildString = "$buildString $separator $element"
                }
            }
            return buildString
        }
    }
}