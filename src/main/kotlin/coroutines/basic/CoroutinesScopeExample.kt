package coroutines.basic

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import kotlin.coroutines.EmptyCoroutineContext

class CoroutinesScopeExample {

    /**
     * start time:20:47.511
     * a: 20:47.603, threadName: DefaultDispatcher-worker-2
     * b: 20:47.603, threadName: DefaultDispatcher-worker-2
     * d: 20:47.609, threadName: DefaultDispatcher-worker-3
     * e: 20:47.610, threadName: DefaultDispatcher-worker-3
     * c: 20:48.613, threadName: DefaultDispatcher-worker-2
     */
    fun execCoroutinesScope() {
        // 複数のバックグラウンドスレッドのうち一つ
        val scope = CoroutineScope(EmptyCoroutineContext)

        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("mm:ss.SSS")
        println("start time:${LocalTime.now().format(formatter)}")

        scope.launch {
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
                delay(3000L)
                println("f: ${LocalTime.now().format(formatter)}, threadName: $threadName")
            }
        }

        // runBlockingと違い待機処理が必要。
        Thread.sleep(2000L)

        // 3000L後に実行される println("f: ${LocalTime.now().format(formatter)}, threadName: $threadName") は実行されない
        scope.cancel()
    }
}