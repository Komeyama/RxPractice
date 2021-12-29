package operators

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import subscribers.DebugTimeSubscriber
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

class TimerOperatorExample {

    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("mm:ss.SSS")

    /**
     * [Result]
     * threadName: main before start time: 57:45.536
     * threadName: RxCachedThreadScheduler-2, time: 57:46.540, data: 0
     * threadName: RxCachedThreadScheduler-2, time: 57:46.541, completed!
     * threadName: main finish time: 57:47.041
     */
    fun executeTimer() {
        val label = "timer"
        val flowable: Flowable<Long> = Flowable.timer(1000L, TimeUnit.MILLISECONDS, Schedulers.io())
        val threadName = Thread.currentThread().name

        println("label: $label, threadName: $threadName before start time: ${LocalTime.now().format(formatter)}")
        flowable.subscribe(DebugTimeSubscriber(formatter = formatter, label = label))

        Thread.sleep(1500L)
        println("label: $label, threadName: $threadName finish time: ${LocalTime.now().format(formatter)}")
    }
}