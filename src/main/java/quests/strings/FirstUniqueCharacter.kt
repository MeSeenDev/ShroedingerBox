package quests.strings

/**
 * @author Vyacheslav Doroshenko
 */
class FirstUniqueCharacter

fun firstUniqChar(s: String): Int {
    s.groupBy { it }.filter { it.value.size < 2 }.forEach { entry ->
        if (entry.value.size == 1) {
            return s.indexOfFirst { it == entry.key }
        }
    }
    return -1
}
