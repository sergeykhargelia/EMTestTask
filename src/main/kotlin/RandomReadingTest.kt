import java.io.File
import kotlin.system.measureTimeMillis

/** This function takes the list of paths to the files
 *  and reads first blockSize bytes of each file.
 *  It returns the average reading speed.
 */

fun randomReadingTest(pathsForRandomOrderReadingTest: List<String>): Double {
    var dataSize: Long = 0
    val timeElapsed = measureTimeMillis {
        pathsForRandomOrderReadingTest.forEach { path ->
            val data = File(path).inputStream().use { it.readNBytes(blockSize) }
            dataSize += data.size
        }
    }
    return calculateSpeed(dataSize, timeElapsed)
}