package coroutines.basic

import kotlinx.coroutines.*
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class JobExample {

    /**
     * start time:05:17.683
     * a: 05:17.700, threadName: main
     * b: 05:17.700, threadName: main
     * d: 05:17.704, threadName: main
     * e: 05:17.704, threadName: main
     * c: 05:18.703, threadName: main
     * f: 05:20.705, threadName: main
     * job completed!!
     */
    fun execJob1() {
        runBlocking {
            val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("mm:ss.SSS")
            println("start time:${LocalTime.now().format(formatter)}")

            val job1 = launch {
                val threadName = Thread.currentThread().name
                println("a: ${LocalTime.now().format(formatter)}, threadName: $threadName")
                println("b: ${LocalTime.now().format(formatter)}, threadName: $threadName")
                delay(1000L)
                println("c: ${LocalTime.now().format(formatter)}, threadName: $threadName")
            }
            val job2 = launch {
                val threadName = Thread.currentThread().name
                println("d: ${LocalTime.now().format(formatter)}, threadName: $threadName")
                println("e: ${LocalTime.now().format(formatter)}, threadName: $threadName")
                delay(3000L)
                println("f: ${LocalTime.now().format(formatter)}, threadName: $threadName")
            }
            job1.join()
            job2.join()
            println("job completed!!")
        }
    }

    /**
     * start time:05:20.709
     * a: 05:20.713, threadName: main
     * b: 05:20.713, threadName: main
     * d: 05:20.713, threadName: main
     * e: 05:20.714, threadName: main
     * f: 05:23.716, threadName: main
     */
    fun execJob2() {
        runBlocking {
            val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("mm:ss.SSS")
            println("start time:${LocalTime.now().format(formatter)}")

            val job1 = launch {
                val threadName = Thread.currentThread().name
                println("a: ${LocalTime.now().format(formatter)}, threadName: $threadName")
                println("b: ${LocalTime.now().format(formatter)}, threadName: $threadName")
                delay(1000L)
                println("c: ${LocalTime.now().format(formatter)}, threadName: $threadName")
            }
            val job2 = launch {
                val threadName = Thread.currentThread().name
                println("d: ${LocalTime.now().format(formatter)}, threadName: $threadName")
                println("e: ${LocalTime.now().format(formatter)}, threadName: $threadName")
                delay(3000L)
                println("f: ${LocalTime.now().format(formatter)}, threadName: $threadName")
            }
            delay(50)
            job1.cancel() // println("c: ${LocalTime.now().format(formatter)}, threadName: $threadName") は実行されない
        }
    }
}