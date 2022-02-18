import java.io.File
import kotlin.system.measureTimeMillis

/** This function takes the path to the directory,
 *  generates a new file in this directory,
 *  writes random big text into the file
 *  and then reads this text back.
 *  It returns a pair, consisted of reading speed and writing speed
 */

fun sequentialIOTest(directory: String): Pair<Double, Double> {
    val fileName = generateFileName(fileNameLength)
    val text = generateText(sizeForSequentialIO)
    val timeElapsedWriting = measureTimeMillis {
        File("$directory/$fileName").outputStream().use { it.write(text) }
    }
    val timeElapsedReading = measureTimeMillis {
        File("$directory/$fileName").inputStream().use { it.readAllBytes() }
    }
    File("$directory/$fileName").delete()
    return Pair (
        calculateSpeed(sizeForSequentialIO.toLong(), timeElapsedReading),
        calculateSpeed(sizeForSequentialIO.toLong(), timeElapsedWriting)
    )
}