package coroutines.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

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

    /**
     * collect:1, 34:02.531, threadName: main
     * collect:2, 34:03.537, threadName: main
     * collect:3, 34:04.539, threadName: main
     * collect:4, 34:05.541, threadName: main
     * collect:5, 34:06.547, threadName: main
     * finish!:  34:06.547
     */
    fun execFlowDistinctUntilChanged() {
        val flow = flowOf(1, 1, 2, 2, 3, 3, 3, 4, 4, 5, 5, 5, 5, 5).distinctUntilChanged().filter {
            delay(1000L)
            true
        }

        runBlocking {
            val threadName = Thread.currentThread().name
            flow.collect {
                println("collect:$it, ${LocalTime.now().format(formatter)}, threadName: $threadName")
            }
            println("finish!:  ${LocalTime.now().format(formatter)}")
        }
    }

    /**
     * collect:6, 45:48.884, threadName: main
     * collect:7, 45:48.887, threadName: main
     * collect:8, 45:48.887, threadName: main
     * collect:9, 45:48.887, threadName: main
     * collect:10, 45:48.887, threadName: main
     * finish!:  45:48.887
     */
    fun execFlowDrop() {
        val flow = flowOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).distinctUntilChanged().drop(5)
        runBlocking {
            val threadName = Thread.currentThread().name
            flow.collect {
                println("collect:$it, ${LocalTime.now().format(formatter)}, threadName: $threadName")
            }
            println("finish!:  ${LocalTime.now().format(formatter)}")
        }
    }

    /**
     * collect:1, 48:48.210, threadName: main
     * collect:2, 48:48.212, threadName: main
     * collect:3, 48:48.212, threadName: main
     * collect:4, 48:48.212, threadName: main
     * collect:5, 48:48.213, threadName: main
     * finish!:  48:48.213
     */
    fun execFlowTake() {
        val flow = flowOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).distinctUntilChanged().take(5)
        runBlocking {
            val threadName = Thread.currentThread().name
            flow.collect {
                println("collect:$it, ${LocalTime.now().format(formatter)}, threadName: $threadName")
            }
            println("finish!:  ${LocalTime.now().format(formatter)}")
        }
    }
}