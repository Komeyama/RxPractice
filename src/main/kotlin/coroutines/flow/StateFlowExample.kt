package coroutines.flow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import kotlin.coroutines.EmptyCoroutineContext

class StateFlowExample {

    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("mm:ss.SSS")

    /**
     * correct start, value is:1, 45:07.640
     * collect:1, 45:07.647, threadName: DefaultDispatcher-worker-1 <= 購読開始時に現在の値を通知する
     * value is:1, 45:07.647, threadName: DefaultDispatcher-worker-1
     * emit:2, 45:07.652, threadName: DefaultDispatcher-worker-2
     * emit:2, 45:07.652, threadName: DefaultDispatcher-worker-2
     * emit:3, 45:07.652, threadName: DefaultDispatcher-worker-2
     * emit:3, 45:07.652, threadName: DefaultDispatcher-worker-2
     * emit:4, 45:07.652, threadName: DefaultDispatcher-worker-2
     * emit:4, 45:07.652, threadName: DefaultDispatcher-worker-2
     * emit:5, 45:07.652, threadName: DefaultDispatcher-worker-2
     * emit:5, 45:07.652, threadName: DefaultDispatcher-worker-2
     * collect:5, 45:07.655, threadName: DefaultDispatcher-worker-1 <= 連続した変更の場合は最後の値が通知される
     * value is:5, 45:07.655, threadName: DefaultDispatcher-worker-1
     */
    fun execStateFlowNotExistDelay() {
        val scope = CoroutineScope(EmptyCoroutineContext)
        val stateFlow = MutableStateFlow(0) // 初期値の設定が必要

        scope.launch {
            println(
                "correct start, value is:${stateFlow.value}, ${
                    LocalTime.now().format(formatter)
                }"
            ) // valueで現在の値を取得できる
            stateFlow.collect {
                val threadName = Thread.currentThread().name
                println("collect:$it, ${LocalTime.now().format(formatter)}, threadName: $threadName")
                println(
                    "value is:${stateFlow.value}, ${
                        LocalTime.now().format(formatter)
                    }, threadName: $threadName"
                ) // valueで現在の値を取得できる
            }
        }

        stateFlow.value = 1
        scope.launch {
            val threadName = Thread.currentThread().name
            val values = listOf(2, 2, 3, 3, 4, 4, 5, 5)
            values.forEach {
                stateFlow.value = it
                println("emit:$it, ${LocalTime.now().format(formatter)}, threadName: $threadName")
            }
        }

        Thread.sleep(1000L)
    }

    /**
     * correct start, value is:1, 47:15.272
     * collect:1, 47:15.278, threadName: DefaultDispatcher-worker-1 <= 購読開始時に現在の値を通知する
     * value is:1, 47:15.279, threadName: DefaultDispatcher-worker-1
     * collect:2, 47:15.294, threadName: DefaultDispatcher-worker-1
     * emit:2, 47:15.294, threadName: DefaultDispatcher-worker-2
     * value is:2, 47:15.295, threadName: DefaultDispatcher-worker-1
     * emit:2, 47:15.305, threadName: DefaultDispatcher-worker-2 <= 値に変更がない場合は通知されない
     * emit:3, 47:15.315, threadName: DefaultDispatcher-worker-2
     * collect:3, 47:15.315, threadName: DefaultDispatcher-worker-1
     * value is:3, 47:15.315, threadName: DefaultDispatcher-worker-1
     * emit:3, 47:15.326, threadName: DefaultDispatcher-worker-2 <= 値に変更がない場合は通知されない
     * emit:4, 47:15.336, threadName: DefaultDispatcher-worker-2
     * collect:4, 47:15.336, threadName: DefaultDispatcher-worker-2
     * value is:4, 47:15.336, threadName: DefaultDispatcher-worker-2
     * emit:4, 47:15.347, threadName: DefaultDispatcher-worker-2 <= 値に変更がない場合は通知されない
     * emit:5, 47:15.358, threadName: DefaultDispatcher-worker-2
     * collect:5, 47:15.358, threadName: DefaultDispatcher-worker-1
     * value is:5, 47:15.358, threadName: DefaultDispatcher-worker-1
     * emit:5, 47:15.369, threadName: DefaultDispatcher-worker-2 <= 値に変更がない場合は通知されない
     */
    fun execStateFlowExistDelay() {
        val scope = CoroutineScope(EmptyCoroutineContext)
        val stateFlow = MutableStateFlow(0) // 初期値の設定が必要

        scope.launch {
            println(
                "correct start, value is:${stateFlow.value}, ${
                    LocalTime.now().format(formatter)
                }"
            ) // valueで現在の値を取得できる
            stateFlow.collect {
                val threadName = Thread.currentThread().name
                println("collect:$it, ${LocalTime.now().format(formatter)}, threadName: $threadName")
                println(
                    "value is:${stateFlow.value}, ${
                        LocalTime.now().format(formatter)
                    }, threadName: $threadName"
                ) // valueで現在の値を取得できる
            }
        }

        stateFlow.value = 1
        scope.launch {
            val threadName = Thread.currentThread().name
            val values = listOf(2, 2, 3, 3, 4, 4, 5, 5)
            values.forEach {
                delay(10L)
                stateFlow.value = it
                println("emit:$it, ${LocalTime.now().format(formatter)}, threadName: $threadName")
            }
        }

        Thread.sleep(1000L)
    }
}