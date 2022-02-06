package coroutines.basic

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class SuspendExample {

    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("mm:ss.SSS")

    /**
     * start time:35:40.122
     * time: 35:41.152, index: 1, threadName: main
     * time: 35:43.152, index: 2, threadName: main
     * deferred1 + deferred2 = 3, time: 35:43.152
     */
    fun execSuspend() {
        runBlocking {
            println("start time:${LocalTime.now().format(formatter)}")
            val deferred1 = async {
                exp(1, 1000L)
            }
            val deferred2 = async {
                exp(2, 3000L)
            }
            val deferred1Value = deferred1.await()
            val deferred2Value = deferred2.await()
            println(
                "deferred1 + deferred2 = ${deferred1Value + deferred2Value}, time: ${
                    LocalTime.now().format(formatter)
                }"
            )
        }
    }

    private suspend fun exp(index: Int, delayMillTime: Long): Int {
        val threadName = Thread.currentThread().name
        delay(delayMillTime)
        println("time: ${LocalTime.now().format(formatter)}, index: $index, threadName: $threadName")
        return index
    }
}