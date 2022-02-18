import java.io.File
import kotlin.system.measureTimeMillis

/** This function takes the list of paths to the directories,
 *  generates a new file in each directory
 *  and then writes random small texts into these files.
 *  It returns the average writing speed.
 */

fun randomWritingTest(directoriesForRandomOrderWritingTest: List<String>): Double {
    val fileName = generateFileName(fileNameLength)
    val text = generateText(blockSize)
    val timeElapsed = measureTimeMillis {
        directoriesForRandomOrderWritingTest.forEach { directory ->
            File("$directory/$fileName").outputStream().use { it.write(text) }
        }
    }
    directoriesForRandomOrderWritingTest.forEach { directory ->
        File("$directory/$fileName").delete()
    }
    return calculateSpeed(blockSize.toLong() * filesCountForRandomOrder, timeElapsed)
}