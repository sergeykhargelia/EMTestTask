@file: JvmName("main")

import java.io.File

const val defaultFolder = "C:/SRC"
const val filesCountForRandomOrder = 10000
const val blockSize = 1024 * 4
const val bytesInMegabyte = 1024 * 1024
const val sizeForSequentialIO = bytesInMegabyte * 100
const val fileNameLength = 30
const val runsCount = 5

fun getFolder(args: Array<String>) =
    if (args.isEmpty()) File(defaultFolder) else File(args[0])

fun calculateSpeed(bytesCount: Long, timeElapsedMilliseconds: Long) =
    bytesCount.toDouble() / bytesInMegabyte / (timeElapsedMilliseconds.toDouble() / 1000.0)

fun generateText(length: Int) =
    (1..length).map { (('a'..'z') + ('A'..'Z') + ('0'..'9')).random() }.joinToString("").toByteArray()

fun generateFileName(length: Int) =
    (1..length).map { ('a'..'z').random() }.joinToString("") + ".txt"

fun printSpeed(message: String, sumOfSpeeds: Double) =
    println("$message ${sumOfSpeeds / runsCount} MB per second")

fun main(args: Array<String>) {
    val initialFolder = getFolder(args)

    var sumOfRandomReadingSpeeds = 0.0
    var sumOfRandomWritingSpeeds = 0.0
    var sumOfSequentialReadingSpeeds = 0.0
    var sumOfSequentialWritingSpeeds = 0.0

    repeat(runsCount) {
        val pathsForRandomOrderReadingTest =
            initialFolder.walk().filter { it.isFile }.filter { it.length() >= blockSize }
                .shuffled().take(filesCountForRandomOrder).map {
                    file -> file.absolutePath
                }.toList()

        val directoriesForRandomOrderWritingTest =
                initialFolder.walk().filter { it.isDirectory }.shuffled().take(filesCountForRandomOrder).map {
                    file -> file.absolutePath
                }.toList()

        val directoriesForSequentialIO = initialFolder.walk().filter { it.isDirectory }.toList().random().absolutePath

        sumOfRandomReadingSpeeds += randomReadingTest(pathsForRandomOrderReadingTest)
        sumOfRandomWritingSpeeds += randomWritingTest(directoriesForRandomOrderWritingTest)
        val (sequentialReadingSpeed, sequentialWritingSpeed) = sequentialIOTest(directoriesForSequentialIO)
        sumOfSequentialReadingSpeeds += sequentialReadingSpeed
        sumOfSequentialWritingSpeeds += sequentialWritingSpeed
    }

    printSpeed("Reading speed for random order of files:", sumOfRandomReadingSpeeds)
    printSpeed("Writing speed for random order of files:", sumOfRandomWritingSpeeds)
    printSpeed("Sequential reading speed:", sumOfSequentialReadingSpeeds)
    printSpeed("Sequential writing speed:", sumOfSequentialWritingSpeeds)
}
