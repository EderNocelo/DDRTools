package com.mx.ddrtools.utils

import java.text.SimpleDateFormat
import java.util.*

class DDRDate {
    companion object {
        /**
         * Function stringDateToTimestamp
         * Convert string date in dd-MM-yyyy format to Timestamp (Long)
         * @param date String
         * @return Long
         * */
        fun stringDateToTimestamp(date: String): Long {
            val formatter = SimpleDateFormat("dd-MM-yyyy")
            val newDate = formatter.parse(date) as Date
            return newDate.time
        }

        /**
         * Function timestampToStringDate
         * Convert Timestamp date (Long) to Date in String (default format: dd/MM/yyyy HH:mm:ss)
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
         * Function monthNameByIndex
         * Get month name by position (01 January, 02 February, etc..). It can receive String or Int position (01,1)
         * @param position Any
         * @return String
         */
        fun monthNameByPosition(position: Any, upperCased:Boolean = false): String {
            if (position is String || position is Int){
                var idx = 0
                idx = if (position is String){
                    position.toInt()
                }else{
                    position as Int
                }
                if (idx in 1..12){
                    val month = String.format("%02d",idx)
                    val monthName = when {
                        month.equals("01", ignoreCase = true) -> "Enero"
                        month.equals("02", ignoreCase = true) -> "Febrero"
                        month.equals("03", ignoreCase = true) -> "Marzo"
                        month.equals("04", ignoreCase = true) -> "Abril"
                        month.equals("05", ignoreCase = true) -> "Mayo"
                        month.equals("06", ignoreCase = true) -> "Junio"
                        month.equals("07", ignoreCase = true) -> "Julio"
                        month.equals("08", ignoreCase = true) -> "Agosto"
                        month.equals("09", ignoreCase = true) -> "Septiembre"
                        month.equals("10", ignoreCase = true) -> "Octubre"
                        month.equals("11", ignoreCase = true) -> "Noviembre"
                        month.equals("12", ignoreCase = true) -> "Diciembre"
                        else -> ""
                    }
                    return if (upperCased) monthName.toUpperCase(Locale.getDefault()) else monthName
                }else{
                    return "$idx is an unknown Month"
                }

            }else{
                return "${position.javaClass} is unsupported"
            }
        }

    }
}