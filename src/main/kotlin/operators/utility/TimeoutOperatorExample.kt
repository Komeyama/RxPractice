package operators.utility

import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import subscribers.DebugSubscriber
import java.util.concurrent.TimeUnit

class TimeoutOperatorExample {

    /**
     * [Result]
     * threadName: main, data: 1
     * threadName: main, data: 2
     * threadName: RxComputationThreadPool-1, throwable: java.util.concurrent.TimeoutException: The source did not signal an event for 1000 milliseconds and has been terminated.
     */
    fun executeTimeout() {
        val label = "timeout"

        val flowable: Flowable<Long> =
            Flowable.create({ emitter ->
                emitter.onNext(1L)
                emitter.onNext(2L)
                try {
                    Thread.sleep(1200L)
                } catch (e: Exception) {
                    emitter.onError(e)
                }
                emitter.onNext(3)
                emitter.onComplete()
            }, BackpressureStrategy.BUFFER)

        flowable.timeout(1000L, TimeUnit.MILLISECONDS).subscribe(DebugSubscriber(label = label))
        Thread.sleep(1000L)
    }
}