package operators

import io.reactivex.rxjava3.core.Flowable
import subscribers.DebugSubscriber
import java.util.concurrent.TimeUnit

class SkipUntilOperatorExample {

    /**
     * [Result]
     * threadName: RxComputationThreadPool-1, data: 3
     * threadName: RxComputationThreadPool-1, data: 4
     * threadName: RxComputationThreadPool-1, data: 5
     */
    fun executeSkipUntil() {
        val label = "skip_until"
        val flowable: Flowable<Long> =
            Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .skipUntil(Flowable.timer(1000L, TimeUnit.MILLISECONDS))

        flowable.subscribe(DebugSubscriber(label = label))
        Thread.sleep(2000L)
    }
}