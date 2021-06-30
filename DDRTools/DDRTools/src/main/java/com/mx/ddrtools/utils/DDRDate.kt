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

import android.text.format.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DDRDate {
    companion object {
        /**
         * Convert string date of 'dd-MM-yyyy' format to Timestamp (Long)
         * @param date String
         * @return Long
         * */
        fun stringDateToTimestamp(date: String): Long {
            val formatter = SimpleDateFormat("dd-MM-yyyy")
            val newDate = formatter.parse(date) as Date
            return newDate.time
        }

        /**
         * Convert Timestamp date (Long) to String Date (default format: dd/MM/yyyy HH:mm:ss)
         * @param date Long
         * @return String
         * */
        fun timestampToStringDate(date: Long, format: String? = null): String {
            val formatter = format?.let {
                it
            } ?: run {
                "dd/MM/yyyy HH:mm:ss"
            }
            val newDate = Date(date)
            return formatter.format(newDate)
        }

        /**
         * Get month name by position. It can receive String or Int position ('01',1)
         * Ex: 01=Enero | 2=Febrero | 3=MARZO
         * @param position Any
         * @return String
         */
        fun monthNameByPosition(position: Any, upperCased: Boolean = false, shortName: Boolean = false): String {
            if (position is String || position is Int) {
                var idx = 0
                idx = if (position is String) {
                    position.toInt()
                } else {
                    position as Int
                }
                if (idx in 1..12) {
                    val month = String.format("%02d", idx)
                    val monthName = when {
                        month.equals("01", ignoreCase = true) -> if (shortName) "ENE" else "Enero"
                        month.equals("02", ignoreCase = true) -> if (shortName) "FEB" else "Febrero"
                        month.equals("03", ignoreCase = true) -> if (shortName) "MAR" else "Marzo"
                        month.equals("04", ignoreCase = true) -> if (shortName) "ABR" else "Abril"
                        month.equals("05", ignoreCase = true) -> if (shortName) "MAY" else "Mayo"
                        month.equals("06", ignoreCase = true) -> if (shortName) "JUN" else "Junio"
                        month.equals("07", ignoreCase = true) -> if (shortName) "JUL" else "Julio"
                        month.equals("08", ignoreCase = true) -> if (shortName) "AGO" else "Agosto"
                        month.equals("09", ignoreCase = true) -> if (shortName) "SEP" else "Septiembre"
                        month.equals("10", ignoreCase = true) -> if (shortName) "OCT" else "Octubre"
                        month.equals("11", ignoreCase = true) -> if (shortName) "NOV" else "Noviembre"
                        month.equals("12", ignoreCase = true) -> if (shortName) "DIC" else "Diciembre"
                        else -> ""
                    }
                    return if (upperCased) monthName.toUpperCase(Locale.getDefault()) else monthName
                } else {
                    return "$idx is an unknown Month"
                }

            } else {
                return "${position.javaClass} is unsupported"
            }
        }

        /**
         * Convert an string date to pretty format.
         * Ex: '2020-01-01 00:00:00' -> 01 de Enero del 2020 a las 00:00:00
         * @param format String
         * @param stringDate String
         * @return String
         */
        fun parseStringDateToPrettyDate(format: String, stringDate: String): String {
            val simpleDateFormat = SimpleDateFormat(format, Locale.US)
            val currentDate: Date
            try {
                currentDate = simpleDateFormat.parse(stringDate)!!
            } catch (e: ParseException) {
                return "Format is not supported"
            }
            val dayNumber = DateFormat.format("dd", currentDate) as String
            val monthNumber = DateFormat.format("MM", currentDate) as String
            val time = DateFormat.format("HH:mm:ss", currentDate) as String
            return "$dayNumber de ${monthNameByPosition(monthNumber)} a las $time Hrs."
        }



    }
}