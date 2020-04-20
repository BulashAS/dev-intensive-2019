package ru.skillbranch.devintensive.extensions

fun String.truncate(length: Int = 16): String {
    val s = this.trim()
    return when {
        s.length > length -> "${s.substring(0, length).trimEnd()}..."
        else -> s
    }
}

fun String.stripHtml(): String {
    var s = this
    //удаляем теги, т.е. куски <...>
    while (s.indexOf('>') > 0) {
        val begin = s.indexOf('<')
        val end = s.indexOf('>')
        s = s.removeRange(begin, end + 1)
    }
    for (ch in "&<>'\"")
        s=s.replace(ch.toString(),"")
    while (s.contains("  "))
        s = s.replace("  ", " ")
    return s
}

//удаляет все вхождения заданного символа и возвращает полученную строку
fun String.killChar(ch: Char): String {
    var s = this
    var i = s.indexOf(ch)
    while (i > -1) {
        s = s.removeRange(i, i + 1)
        i = s.indexOf(ch)
    }
    return s
}