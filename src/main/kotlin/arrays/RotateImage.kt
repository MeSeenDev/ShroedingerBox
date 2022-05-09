package arrays

class RotateImage {

    /**
     * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * Output: [[7,4,1],[8,5,2],[9,6,3]]
     * Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
     * Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
     */
    fun rotate(matrix: Array<IntArray>) {
        val vik = Array(matrix.size) { IntArray(matrix.size) }
        for (i in matrix.indices) {
            var x = matrix.size - 1
            for (j in matrix.indices) {
                vik[i][j] = matrix[x][i]
                x--
            }
        }
        for (i in matrix.indices) {
            for (j in matrix.indices) {
                matrix[i][j] = vik[i][j]
            }
        }

    }


}