package quests.arrays.twosum

/**
 * @author Vyacheslav Doroshenko
 */
fun twoSumSimpleKt(nums: IntArray, target: Int): IntArray {
    for (i in nums.indices) {
        val num = i + 1
        for (j in num until nums.size) {
            if (nums[i] + nums[j] == target) {
                return intArrayOf(i, j)
            }
        }
    }
    return intArrayOf()
}

fun twoSumMidle(nums: IntArray, target: Int): IntArray {
    val map = nums.mapIndexed { index, i -> i to index }.toMap()
    nums.onEachIndexed() { index, num ->
        if (map.containsKey(target.minus(num)) && map[target.minus(num)] != index) {
            return intArrayOf(index, map[target - num]!!)
        }
    }
    return intArrayOf()
}

fun twoSumMore(nums: IntArray, target: Int): IntArray {
    val map = nums.mapIndexed { index, i -> i to index }.toMap()
    for((index, num) in nums.withIndex()){
        if (map.containsKey(target.minus(num)) && map[target.minus(num)] != index) {
            return intArrayOf(index, map[target - num]!!)
        }
    }
    return intArrayOf()
}