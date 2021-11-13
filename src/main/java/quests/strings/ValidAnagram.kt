package quests.strings

/**
 * @author Vyacheslav Doroshenko
 */
fun isAnagram(s: String, t: String): Boolean =
    s.toCharArray().apply { sort() }.contentEquals(t.toCharArray().apply { sort() })

fun isAnagramProper(s: String, t: String)
        : Boolean = if (s.length != t.length) false else
    IntArray(26) { 0 }.let { count ->
        s.forEach { ch ->
            ++count[ch - 'a']
        }
        t.onEach { ch ->
            if (count[ch - 'a'] <= 0) {
                return false
            }
            --count[ch - 'a']
        }
        return true
    }

fun isAnagramSpeedUp0(s: String, t: String): Boolean =
    if (s.length != t.length) false
    else Array(26) { 0 }.let { array ->
        s.indices.onEach { index ->
            ++array[s[index] - 'a']
            --array[t[index] - 'a']
        }
        array.onEach { count ->
            if (count != 0) return false
        }
        return true
    }

