package operators

import io.reactivex.rxjava3.core.Flowable
import subscribers.DebugSubscriber
import java.util.concurrent.TimeUnit

class SkipOperatorExample {

    /**
     * [Result]
     * threadName: RxComputationThreadPool-1, data: 3
     * threadName: RxComputationThreadPool-1, data: 4
     * threadName: RxComputationThreadPool-1, data: 5
     */
    fun executeSkip() {
        val label = "take_skip"
        val flowable: Flowable<Long> =
            Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .skip(3)

        flowable.subscribe(DebugSubscriber(label = label))
        Thread.sleep(2000L)
    }
}