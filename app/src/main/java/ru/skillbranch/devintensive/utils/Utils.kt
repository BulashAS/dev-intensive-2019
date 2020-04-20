package ru.skillbranch.devintensive.utils

import java.util.*

object Utils {
//map.getOrDefault(it.toLowerCase(), it.toString())
//stringBuilder
//buildString { }
    fun transliteration(payload: String, divider: String = " "): String {
        val slova = payload.split(" ")
        val words = mutableListOf<String>()
        for (slovo in slova) {
            val literals = mutableListOf<String>()
            for (bukva in slovo) {
                literals.add(
                    if (bukva.isUpperCase()) {
                        val b = abc[bukva.toLowerCase().toString()] ?: bukva.toString()
                        if (b.length > 1)
                            "${b[0].toUpperCase()}${b[1]}"
                        else
                            b.toUpperCase(Locale.ROOT)
                    } else
                        abc[bukva.toString()] ?: bukva.toString()
                )
            }
            words.add(literals.joinToString(separator = ""))
        }
        return words.joinToString(separator = divider)
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val f: String =
            if (firstName.isNullOrBlank()) "" else firstName.capitalize()[0].toString() +
                    if (lastName.isNullOrBlank()) "" else lastName.capitalize()[0].toString()
        return if (f.isEmpty()) null else f
    }

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")
        val firstName = if (parts?.getOrNull(0).isNullOrEmpty()) null else parts!![0]
        val lastName = if (parts?.getOrNull(1).isNullOrEmpty()) null else parts!![1]
        return firstName to lastName
    }

    val abc = mapOf(
        "а" to "a",
        "б" to "b",
        "в" to "v",
        "г" to "g",
        "д" to "d",
        "е" to "e",
        "ё" to "e",
        "ж" to "zh",
        "з" to "z",
        "и" to "i",
        "й" to "i",
        "к" to "k",
        "л" to "l",
        "м" to "m",
        "н" to "n",
        "о" to "o",
        "п" to "p",
        "р" to "r",
        "с" to "s",
        "т" to "t",
        "у" to "u",
        "ф" to "f",
        "х" to "h",
        "ц" to "c",
        "ч" to "ch",
        "ш" to "sh",
        "щ" to "sh'",
        "ъ" to "",
        "ы" to "i",
        "ь" to "",
        "э" to "e",
        "ю" to "yu",
        "я" to "ya"
    )
}