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
     * start time: 25:38.405
     * notification time: 25:40.414, data: A
     * threadName: RxComputationThreadPool-1, time: 25:40.414, data: A
     * notification time: 25:40.414, data: B
     * threadName: RxComputationThreadPool-1, time: 25:40.414, data: B
     * notification time: 25:40.414, data: C
     * threadName: RxComputationThreadPool-1, time: 25:40.414, data: C
     * threadName: RxComputationThreadPool-1, time: 25:40.414, completed!
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

        flowable.delay(2000L, TimeUnit.MILLISECONDS)
            .doOnNext { data ->
                val currentTime = LocalTime.now().format(formatter)
                println("notification time: $currentTime, data: $data")
            }.subscribe(DebugTimeSubscriber(formatter = formatter, label = label))

        Thread.sleep(5000L)
    }
}