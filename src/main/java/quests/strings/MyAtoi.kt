package quests.strings

class MyAtoi {

    fun myAtoi0(s: String): Int {
        kotlin.runCatching {
            s.replace("""[^[-+]?[0-9]+[0-9]*]""".toRegex(), "").toInt()
        }.onSuccess { result ->
            return result
        }
        return 0
    }

    fun myAtoi(s: String): Int = try {
        s.replace("""[^[-+]?[0-9]+[0-9]*]""".toRegex(), "").toInt()
    } catch (thr: Throwable) {
        0
    }
}