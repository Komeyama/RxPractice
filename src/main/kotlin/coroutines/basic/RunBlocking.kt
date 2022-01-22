package coroutines.basic

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class RunBlocking {

    /**
     * start time:18:08.960
     * a: 18:09.267, threadName: main
     * b: 18:09.267, threadName: main
     * d: 18:09.278, threadName: main
     * e: 18:09.278, threadName: main
     * c: 18:10.274, threadName: main
     */
    fun execRunBlocking() {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("mm:ss.SSS")
        println("start time:${LocalTime.now().format(formatter)}")
        runBlocking {
            launch {
                val threadName = Thread.currentThread().name
                println("a: ${LocalTime.now().format(formatter)}, threadName: $threadName")
                println("b: ${LocalTime.now().format(formatter)}, threadName: $threadName")
                delay(1000L)
                println("c: ${LocalTime.now().format(formatter)}, threadName: $threadName")
            }
            launch {
                val threadName = Thread.currentThread().name
                println("d: ${LocalTime.now().format(formatter)}, threadName: $threadName")
                println("e: ${LocalTime.now().format(formatter)}, threadName: $threadName")
            }
        }
    }
}