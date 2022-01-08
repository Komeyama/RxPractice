package operators.utility

import io.reactivex.rxjava3.core.Flowable
import subscribers.DebugTimeSubscriber
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

class RepeatUntilOperatorExample {

    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("mm:ss.SSS")

    /**
     * [Result]
     * threadName: RxComputationThreadPool-1, time: 03:39.709, data: 0
     * threadName: RxComputationThreadPool-1, time: 03:39.787, data: 1
     * threadName: RxComputationThreadPool-1, time: 03:39.886, data: 2
     * called: elapsed time is 469
     * threadName: RxComputationThreadPool-2, time: 03:39.989, data: 0
     * threadName: RxComputationThreadPool-2, time: 03:40.088, data: 1
     * threadName: RxComputationThreadPool-2, time: 03:40.188, data: 2
     * called: elapsed time is 770
     * threadName: RxComputationThreadPool-2, time: 03:40.189, completed!
     */
    fun executeRepeatUntil() {
        val label = "repeat_until"

        val startTime = System.currentTimeMillis()
        val flowable: Flowable<Long> =
            Flowable.interval(100L, TimeUnit.MILLISECONDS)
                .take(3)
                .repeatUntil {
                    val elapsedTime = System.currentTimeMillis() - startTime
                    println("called: elapsed time is $elapsedTime")
                    elapsedTime > 500L
                }

        flowable.subscribe(DebugTimeSubscriber(formatter = formatter, label = label))
        Thread.sleep(1000L)
    }
}