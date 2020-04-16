package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.models.UserView
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

fun User.toUserView(): UserView {
    val fullName = "$firstName $lastName"
    val nickName = Utils.transliteration(fullName)
    val status =
        if (lastVisit == null) "Ещё ни разу не был" else if (isOnline) "online" else "Последний раз был ${lastVisit.humanizeDiff()}"
    val initials = Utils.toInitials(firstName, lastName)
    return UserView(
        id,
        fullName = fullName,
        nickName = nickName,
        avatar = avatar,
        status = status,
        initials = initials
    )
}
