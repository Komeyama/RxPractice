package coroutines.basic

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class AsyncExample {

    /**
     * start time:21:56.510
     * deferred1:1, time: 21:57.537, threadName: main
     * deferred2:5, time: 21:59.535, threadName: main
     * deferred1 + deferred2 = 6, time: 21:59.536, threadName: main
     */
    fun execAsync() {
        runBlocking {
            val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("mm:ss.SSS")
            println("start time:${LocalTime.now().format(formatter)}")
            var threadName1 = ""
            var threadName2 = ""

            val deferred1 = async {
                threadName1 = Thread.currentThread().name
                delay(1000L)
                1
            }
            val deferred2 = async {
                threadName2 = Thread.currentThread().name
                delay(3000L)
                2 + 3
            }
            val deferred1Value = deferred1.await()
            println("deferred1:$deferred1Value, time: ${LocalTime.now().format(formatter)}, threadName: $threadName1")
            val deferred2Value = deferred2.await()
            println("deferred2:$deferred2Value, time: ${LocalTime.now().format(formatter)}, threadName: $threadName2")
            println(
                "deferred1 + deferred2 = ${deferred1Value + deferred2Value}, time: ${
                    LocalTime.now().format(formatter)
                }, threadName: $threadName2"
            )
        }
    }
}