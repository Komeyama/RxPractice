package coroutines.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class FlowExample {

    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("mm:ss.SSS")

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

    /**
     * collect:1, 10:39.329, threadName: main
     * emit:1, 10:39.331, threadName: main
     * catch:java.lang.Exception: error!, 10:40.340, threadName: main
     */
    fun execFlow2() {
        val flow = flow {
            val threadName = Thread.currentThread().name
            val values = listOf(1, 10)
            values.forEach {
                emit(it)
                println("emit:$it, ${LocalTime.now().format(formatter)}, threadName: $threadName")
                delay(1000L)
                throw Exception("error!")
            }
        }

        runBlocking {
            val threadName = Thread.currentThread().name
            flow.catch {
                println("catch:$it, ${LocalTime.now().format(formatter)}, threadName: $threadName")
            }.collect {
                println("collect:$it, ${LocalTime.now().format(formatter)}, threadName: $threadName")
            }
        }
    }

    /**
     * collect:2, 06:43.711, threadName: main
     * collect:4, 06:44.719, threadName: main
     * collect:6, 06:45.729, threadName: main
     * collect:8, 06:46.740, threadName: main
     * collect:10, 06:47.748, threadName: main
     */
    fun execFlowMap() {
        val flow = flowOf(1, 2, 3, 4, 5).map {
            delay(1000L)
            it * 2
        }

        runBlocking {
            val threadName = Thread.currentThread().name
            flow.collect {
                println("collect:$it, ${LocalTime.now().format(formatter)}, threadName: $threadName")
            }
        }
    }

    /**
     * collect:2, 25:09.060, threadName: main
     * collect:4, 25:11.067, threadName: main
     */
    fun execFlowFilter() {
        val flow = flowOf(1, 2, 3, 4, 5).filter {
            delay(1000L)
            it % 2 == 0
        }

        runBlocking {
            val threadName = Thread.currentThread().name
            flow.collect {
                println("collect:$it, ${LocalTime.now().format(formatter)}, threadName: $threadName")
            }
        }
    }
}