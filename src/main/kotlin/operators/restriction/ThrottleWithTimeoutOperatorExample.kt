package operators.restriction

import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import subscribers.DebugSubscriber
import java.util.concurrent.TimeUnit

class ThrottleWithTimeoutOperatorExample {

    /**
     * [Result]
     * threadName: RxComputationThreadPool-1, data: A
     * threadName: RxComputationThreadPool-1, data: D
     * threadName: main, data: E
     * threadName: main completed!
     */
    fun executeThrottleWithTimeout() {
        val label = "throttle_with_timeout"
        val flowable: Flowable<String> =
            Flowable.create({ emitter ->
                emitter.onNext("A")
                Thread.sleep(510L)

                emitter.onNext("B")
                Thread.sleep(490L)

                emitter.onNext("C")
                Thread.sleep(490L)

                emitter.onNext("D")
                Thread.sleep(510L)

                emitter.onNext("E")
                Thread.sleep(490L)

                emitter.onComplete()
            }, BackpressureStrategy.BUFFER)

        flowable
            .throttleWithTimeout(500L, TimeUnit.MILLISECONDS)
            .subscribe(DebugSubscriber(label = label))
        Thread.sleep(4000L)
    }
}