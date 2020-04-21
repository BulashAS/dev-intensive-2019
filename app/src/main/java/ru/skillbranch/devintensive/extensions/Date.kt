package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.absoluteValue
//import kotlin.time.hours

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR


fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    this.time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val delta = (date.time - this.time).absoluteValue
    val past = (date.time >= this.time)
    var s = when {
        delta <= SECOND -> "только что"
        delta <= 45 * SECOND -> "несколько секунд"
        delta <= 75 * SECOND -> "минуту"
        delta <= 45 * MINUTE -> TimeUnits.MINUTE.plural(((delta / MINUTE) + if (delta % MINUTE < 30 * SECOND) 0 else 1).toInt())
        delta <= 75 * MINUTE -> "час"
        delta <= 22 * HOUR -> TimeUnits.HOUR.plural(((delta / HOUR) + if (delta % HOUR < 30 * MINUTE) 0 else 1).toInt())
        delta <= 26 * HOUR -> "день"
        delta <= 360 * 24 * HOUR -> TimeUnits.DAY.plural(((delta / (24 * HOUR)) + if (delta % (24 * HOUR) < 12 * HOUR) 0 else 1).toInt())
        else -> ""
    }
    s = if (past)
        "$s назад"
    else
        "через $s"
    if (delta > 360 * 24 * HOUR)
        s = if (past) "более года назад" else "более чем через год"
    return s
}

enum class TimeUnits {
    SECOND, MINUTE, HOUR, DAY;

    enum class TimePlurals {
        ONE, FEW, MANY
    }

    fun plural(value: Int): String {
        val number = value.absoluteValue
        val howMuch = when {
            number % 10 == 1 && number % 100 != 11 -> TimePlurals.ONE
            number % 10 in 2..4 && (number % 100 < 10 || number % 100 >= 20) -> TimePlurals.FEW
            else -> TimePlurals.MANY
        }
        val rez = mapOf(
            Pair(SECOND, TimePlurals.ONE) to "секунду",
            Pair(SECOND, TimePlurals.FEW) to "секунды",
            Pair(SECOND, TimePlurals.MANY) to "секунд",
            Pair(MINUTE, TimePlurals.ONE) to "минуту",
            Pair(MINUTE, TimePlurals.FEW) to "минуты",
            Pair(MINUTE, TimePlurals.MANY) to "минут",
            Pair(HOUR, TimePlurals.ONE) to "час",
            Pair(HOUR, TimePlurals.FEW) to "часа",
            Pair(HOUR, TimePlurals.MANY) to "часов",
            Pair(DAY, TimePlurals.ONE) to "день",
            Pair(DAY, TimePlurals.FEW) to "дня",
            Pair(DAY, TimePlurals.MANY) to "дней"
        )
        return "$value ${rez[Pair(this, howMuch)]}"
    }
}

