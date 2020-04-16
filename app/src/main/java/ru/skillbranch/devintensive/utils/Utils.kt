package ru.skillbranch.devintensive.utils

object Utils {

    fun transliteration(payload: String, divider: String = " "): String {
        TODO("not implemated")
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        TODO()
    }


    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")
        val firstName = if (parts?.getOrNull(0).isNullOrEmpty()) null else parts!![0]
        val lastName = if (parts?.getOrNull(1).isNullOrEmpty()) null else parts!![1]
        return firstName to lastName
    }
}