package fake


inline fun exec(inputString: () -> String) {
    System.setIn(inputString().byteInputStream())
}
