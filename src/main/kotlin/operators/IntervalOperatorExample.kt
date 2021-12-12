package operators

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

class IntervalOperatorExample {

    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("mm:ss.SSS")


    /**
     * [Result]
     * thread name: main before start          time: 02:46.995
     * thread name: RxCachedThreadScheduler-1, time: 02:48.017, data: 0
     * thread name: RxCachedThreadScheduler-1, time: 02:49.016, data: 1
     * thread name: RxCachedThreadScheduler-1, time: 02:50.015, data: 2
     * thread name: RxCachedThreadScheduler-1, time: 02:51.013, data: 3
     * thread name: RxCachedThreadScheduler-1, time: 02:52.017, data: 4
     * thread name: main finish                time: 02:52.021
     */
    fun executeInterval() {
        val label = "interval"
        val flowable: Flowable<Long> = Flowable.interval(1000L, TimeUnit.MILLISECONDS, Schedulers.io())
        val threadName = Thread.currentThread().name
        println("label: $label, thread name: $threadName before start time: ${LocalTime.now().format(formatter)}")

        flowable.subscribe { data ->
            val innerThreadName = Thread.currentThread().name
            val currentTime = LocalTime.now().format(formatter)
            println("label: $label, thread name: $innerThreadName, time: $currentTime, data: $data")
        }

        Thread.sleep(5000L)
        println("label: $label, thread name: $threadName finish time: ${LocalTime.now().format(formatter)}")
    }
}