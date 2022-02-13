package coroutines.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Thread.sleep
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class FlowExample {

    /**
     * start time:03:28.832
     * collect1:1, 03:29.185, threadName: main
     * emit:1, 03:29.186, threadName: main
     * collect2:1, 03:29.188, threadName: main
     * emit:1, 03:29.188, threadName: main
     * collect1:10, 03:30.188, threadName: main
     * emit:10, 03:30.189, threadName: main
     * finish collect1!: 03:31.194
     */
    fun execFlow1() {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("mm:ss.SSS")
        println("start time:${LocalTime.now().format(formatter)}")

        val flow = flow {
            val threadName = Thread.currentThread().name
            val values = listOf(1, 10)
            values.forEach {
                emit(it)
                println("emit:$it, ${LocalTime.now().format(formatter)}, threadName: $threadName")
                delay(1000L)
            }
        }

        runBlocking {
            launch {
                flow.collect {
                    val threadName = Thread.currentThread().name
                    println("collect1:$it, ${LocalTime.now().format(formatter)}, threadName: $threadName")
                }
                println("finish collect1!: ${LocalTime.now().format(formatter)}")
            }

            val job = launch {
                flow.collect {
                    val threadName = Thread.currentThread().name
                    println("collect2:$it, ${LocalTime.now().format(formatter)}, threadName: $threadName")
                }
                println("finish collect2!: ${LocalTime.now().format(formatter)}")
            }
            delay(1000L)
            job.cancel()
        }
    }
}