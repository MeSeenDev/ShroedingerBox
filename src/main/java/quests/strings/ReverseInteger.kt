package quests.strings


/**
 * @author Vyacheslav Doroshenko
 */

fun reverse(x: Int): Int =
    x.toString().run {
        try {
            if (contains("-") || contains("+")) {
                val pref = substring(0..0)
                return@run (pref + replace("""\D""".toRegex(), "").reversed()).toInt()
            }
            reversed().toInt()
        } catch (thr: Throwable) {
            0
        }

    }

fun reverse2(x: Int): Int =
    x.run {
        try {
            if (x > 0) {
                x.toString().reversed().toInt()
            } else {
                ("-" + x.toString().reversed().replace("""\D""".toRegex(), "")).toInt()
            }
        } catch (thr: Throwable) {
            0
        }
    }

/**
 * Эталонное решение
 */
fun reverse3(num: Int): Int {
    var x = num
    var rev = 0
    while (x != 0) {
        val pop = x % 10
        x /= 10
        if (rev > Int.MAX_VALUE / 10 || rev == Int.MAX_VALUE / 10 && pop > 7) return 0
        if (rev < Int.MIN_VALUE / 10 || rev == Int.MIN_VALUE / 10 && pop < -8) return 0
        rev = rev * 10 + pop
    }
    return rev
}

