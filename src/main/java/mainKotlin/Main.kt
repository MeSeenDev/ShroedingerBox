package mainKotlin

fun main() {

    val ss: String? = " СССУка"
    println(ss?.ifBlank { " пустой" }?:"Нулевой")

}



val Byte.intVal : Int
    get() {
        val ub = this.toUByte()
        return ub.toInt()
    }

fun byteToUnsigned(b: Byte): UByte{
    return b.toUByte()
}


val arr = listOf( "8e20faa72ba0b470",
    "47107ddd9b505a38",
    "ad08b0e0c3282d1c",
    "d8045870ef14980e",
    "6c022c38f90a4c07",
    "3601161cf205268d",
    "1b8e0b0e798c13c8",
    "83478b07b2468764",
    "a011d380818e8f40",
    "5086e740ce47c920",
    "2843fd2067adea10",
    "14aff010bdd87508",
    "0ad97808d06cb404",
    "05e23c0468365a02",
    "8c711e02341b2d01",
    "46b60f011a83988e",
    "90dab52a387ae76f",
    "486dd4151c3dfdb9",
    "24b86a840e90f0d2",
    "125c354207487869",
    "092e94218d243cba",
    "8a174a9ec8121e5d",
    "4585254f64090fa0",
    "accc9ca9328a8950",
    "9d4df05d5f661451",
    "c0a878a0a1330aa6",
    "60543c50de970553",
    "302a1e286fc58ca7",
    "18150f14b9ec46dd",
    "0c84890ad27623e0",
    "0642ca05693b9f70",
    "0321658cba93c138",
    "86275df09ce8aaa8",
    "439da0784e745554",
    "afc0503c273aa42a",
    "d960281e9d1d5215",
    "e230140fc0802984",
    "71180a8960409a42",
    "b60c05ca30204d21",
    "5b068c651810a89e",
    "456c34887a3805b9",
    "ac361a443d1c8cd2",
    "561b0d22900e4669",
    "2b838811480723ba",
    "9bcf4486248d9f5d",
    "c3e9224312c8c1a0",
    "effa11af0964ee50",
    "f97d86d98a327728",
    "e4fa2054a80b329c",
    "727d102a548b194e",
    "39b008152acb8227",
    "9258048415eb419d",
    "492c024284fbaec0",
    "aa16012142f35760",
    "550b8e9e21f7a530",
    "a48b474f9ef5dc18",
    "70a6a56e2440598e",
    "3853dc371220a247",
    "1ca76e95091051ad",
    "0edd37c48a08a6d8",
    "07e095624504536c",
    "8d70c431ac02a736",
    "c83862965601dd1b",
    "641c314b2b8ee083")

fun hexToDec(s : String): ULong {
    return s.toULong(16)
}










