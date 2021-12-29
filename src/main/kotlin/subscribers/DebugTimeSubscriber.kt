package subscribers

import java.time.LocalTime
import java.time.format.DateTimeFormatter

class DebugTimeSubscriber<T>(private val formatter: DateTimeFormatter, var label: String = "time") :
    DebugSubscriber<T>() {

    override fun onNext(data: T) {
        val threadName = Thread.currentThread().name
        val currentTime = LocalTime.now().format(formatter)
        println("label: $label, threadName: $threadName, time: $currentTime, data: $data")
    }

    override fun onComplete() {
        val threadName = Thread.currentThread().name
        val currentTime = LocalTime.now().format(formatter)
        println("label: $label, threadName: $threadName, time: $currentTime, completed!")
    }
}