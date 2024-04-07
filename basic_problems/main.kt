fun main() {
    val arr = intArrayOf(1, 3, 5, 7, 9)
    Solutions.problemOne(arr)
}

object Solutions {
    fun problemOne(arr: IntArray) {
        val temp = arr
        // Sort the array
        temp.sort()

        // Calculate the sum of all five integers
        val totalSum = temp.sum()

        // Calculate minimum and maximum sum by excluding the smallest and largest number respectively
        val minSum = totalSum - temp.last()
        val maxSum = totalSum - temp.first()


        println("$minSum $maxSum")
    }
}