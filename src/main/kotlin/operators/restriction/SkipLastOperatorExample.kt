package operators.restriction

import io.reactivex.rxjava3.core.Flowable
import subscribers.DebugSubscriber
import java.util.concurrent.TimeUnit

class SkipLastOperatorExample {

    /**
     * [Result]
     * threadName: RxComputationThreadPool-1, data: 0
     * threadName: RxComputationThreadPool-1, data: 1
     * threadName: RxComputationThreadPool-1, data: 2
     * threadName: RxComputationThreadPool-1 completed!
     */
    fun executeSkipLast() {
        val label = "skip_last"
        val flowable: Flowable<Long> =
            Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .take(5)
                .skipLast(2)

        flowable.subscribe(DebugSubscriber(label = label))
        Thread.sleep(2000L)
    }
}