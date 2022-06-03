package quests.strings

import java.util.*

fun isPalindrome(s: String): Boolean =
    s.lowercase(Locale.getDefault()).replace("""\W|_""".toRegex(),"").let {
        it == it.reversed()
    }