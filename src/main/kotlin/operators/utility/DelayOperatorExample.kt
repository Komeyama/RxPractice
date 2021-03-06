package operators.utility

import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import subscribers.DebugTimeSubscriber
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

class DelayOperatorExample {

    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("mm:ss.SSS")

    /**
     * [Result]
     * start subscription time: 01:40.616
     * start time: 01:40.634
     * notification time: 01:42.638, data: A
     * threadName: RxComputationThreadPool-1, time: 01:42.638, data: A
     * notification time: 01:42.638, data: B
     * threadName: RxComputationThreadPool-1, time: 01:42.638, data: B
     * notification time: 01:42.638, data: C
     * threadName: RxComputationThreadPool-1, time: 01:42.638, data: C
     * threadName: RxComputationThreadPool-1, time: 01:42.638, completed!
     */
    fun executeDelay() {
        val label = "delay"
        val flowable: Flowable<String> =
            Flowable.create({ emitter ->
                val statTime = LocalTime.now().format(formatter)
                println("start time: $statTime")
                emitter.onNext("A")
                emitter.onNext("B")
                emitter.onNext("C")
                emitter.onComplete()
            }, BackpressureStrategy.BUFFER)

        val flowableDelay = flowable.delay(2000L, TimeUnit.MILLISECONDS)
            .doOnNext { data ->
                val currentTime = LocalTime.now().format(formatter)
                println("notification time: $currentTime, data: $data")
            }

        val statSubscriptionTime = LocalTime.now().format(formatter)
        println("start subscription time: $statSubscriptionTime")
        flowableDelay.subscribe(DebugTimeSubscriber(formatter = formatter, label = label))

        Thread.sleep(5000L)
    }
}