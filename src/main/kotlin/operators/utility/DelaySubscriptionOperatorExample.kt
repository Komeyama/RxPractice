package operators.utility

import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import subscribers.DebugTimeSubscriber
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

class DelaySubscriptionOperatorExample {

    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("mm:ss.SSS")

    /**
     * [Result]
     * start subscription time: 55:28.240
     * start time: 55:30.290
     * notification time: 55:30.290, data: A
     * threadName: RxComputationThreadPool-1, time: 55:30.290, data: A
     * notification time: 55:30.291, data: B
     * threadName: RxComputationThreadPool-1, time: 55:30.291, data: B
     * notification time: 55:30.291, data: C
     * threadName: RxComputationThreadPool-1, time: 55:30.291, data: C
     * threadName: RxComputationThreadPool-1, time: 55:30.291, completed!
     */
    fun executeDelaySubscription() {
        val label = "delay_subscription"
        val flowable: Flowable<String> =
            Flowable.create({ emitter ->
                val statTime = LocalTime.now().format(formatter)
                println("start time: $statTime")
                emitter.onNext("A")
                emitter.onNext("B")
                emitter.onNext("C")
                emitter.onComplete()
            }, BackpressureStrategy.BUFFER)


        val statSubscriptionTime = LocalTime.now().format(formatter)
        println("start subscription time: $statSubscriptionTime")
        flowable.delaySubscription(2000L, TimeUnit.MILLISECONDS)
            .doOnNext { data ->
                val currentTime = LocalTime.now().format(formatter)
                println("notification time: $currentTime, data: $data")
            }.subscribe(DebugTimeSubscriber(formatter = formatter, label = label))

        Thread.sleep(5000L)
    }
}