package coroutines.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class FlowExample {

    /**
     * start time:41:59.140
     * collect:1, 41:59.375, threadName: main
     * emit:1, 41:59.375, threadName: main
     * collect:10, 42:00.384, threadName: main
     * emit:10, 42:00.384, threadName: main
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
            flow.collect {
                val threadName = Thread.currentThread().name
                println("collect:$it, ${LocalTime.now().format(formatter)}, threadName: $threadName")
            }
        }
    }
}