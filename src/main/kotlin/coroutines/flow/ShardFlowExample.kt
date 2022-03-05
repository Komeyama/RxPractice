package coroutines.flow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import kotlin.coroutines.EmptyCoroutineContext

class ShardFlowExample {

    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("mm:ss.SSS")

    /**
     * emit:1, 26:02.955, threadName: DefaultDispatcher-worker-1
     * collect:1, 26:02.956, threadName: DefaultDispatcher-worker-1
     * emit:10, 26:02.957, threadName: DefaultDispatcher-worker-1
     * collect:10, 26:02.957, threadName: DefaultDispatcher-worker-1
     */
    fun execSharedFlow() {
        val scope = CoroutineScope(EmptyCoroutineContext)
        val mutableSharedFlow = MutableSharedFlow<Int>()

        scope.launch {
            val threadName = Thread.currentThread().name
            val values = listOf(1, 10)
            values.forEach {
                mutableSharedFlow.emit(it)
                println("emit:$it, ${LocalTime.now().format(formatter)}, threadName: $threadName")
            }
        }

        scope.launch {
            mutableSharedFlow.collect {
                val threadName = Thread.currentThread().name
                println("collect:$it, ${LocalTime.now().format(formatter)}, threadName: $threadName")
            }
        }

        Thread.sleep(1000L)
    }
}