package equls


fun Double.trueEquals(other: Any?): Boolean =
    other is Double && this.toString() == other.toString()

/*if (javaClass != other?.javaClass) return false
return this.toString() == (other as Double).toString()
}*/

