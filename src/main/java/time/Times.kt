package time

/**
 * @author Vyacheslav Doroshenko
 */

inline fun countTime(action: () -> Unit) {
    val startTime = System.currentTimeMillis()
    action()
    val stopTime = System.currentTimeMillis()
    println(" Time exec: ${stopTime - startTime} millis")
}