package quests.strings

/**
 * @author Vyacheslav Doroshenko
 */

fun Int.fillWithZeros():String{
    if (this < 99999) {
        val numBuilder = StringBuilder()
        (toString().length..5).onEach {
            numBuilder.append(0)
        }
        return numBuilder.append(this).toString()
    }
    return toString()
}