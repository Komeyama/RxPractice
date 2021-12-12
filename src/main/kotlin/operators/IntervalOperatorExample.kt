package operators

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import subscribers.DebugSubscriber
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

class IntervalOperatorExample {

    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("mm:ss.SSS")

    /**
     * [Result]
     * threadName: main before start          time: 02:46.995
     * threadName: RxCachedThreadScheduler-1, time: 02:48.017, data: 0
     * threadName: RxCachedThreadScheduler-1, time: 02:49.016, data: 1
     * threadName: RxCachedThreadScheduler-1, time: 02:50.015, data: 2
     * threadName: RxCachedThreadScheduler-1, time: 02:51.013, data: 3
     * threadName: RxCachedThreadScheduler-1, time: 02:52.017, data: 4
     * threadName: main finish                time: 02:52.021
     */
    fun executeInterval() {
        val label = "interval"
        val flowable: Flowable<Long> = Flowable.interval(1000L, TimeUnit.MILLISECONDS, Schedulers.io())
        val threadName = Thread.currentThread().name
        println("label: $label, threadName: $threadName before start time: ${LocalTime.now().format(formatter)}")

        flowable.subscribe(IntervalDebugSubscriber(formatter = formatter))

        Thread.sleep(5000L)
        println("label: $label, threadName: $threadName finish time: ${LocalTime.now().format(formatter)}")
    }

    class IntervalDebugSubscriber<T>(private val formatter: DateTimeFormatter, var label: String = "interval") :
        DebugSubscriber<T>() {

        override fun onNext(data: T) {
            val threadName = Thread.currentThread().name
            val currentTime = LocalTime.now().format(formatter)
            println("label: $label, threadName: $threadName, time: $currentTime, data: $data")
        }
    }
}