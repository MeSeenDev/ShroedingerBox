package quests.strings

fun isPalindrome(s: String): Boolean =
    s.toLowerCase().replace("""\W|_""".toRegex(),"").let {
        it == it.reversed()
    }