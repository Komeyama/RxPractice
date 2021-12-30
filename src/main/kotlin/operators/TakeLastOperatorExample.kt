package operators

import io.reactivex.rxjava3.core.Flowable
import subscribers.DebugSubscriber
import java.util.concurrent.TimeUnit

class TakeLastOperatorExample {

    /**
     * [Result]
     * threadName: RxComputationThreadPool-1, data: 3
     * threadName: RxComputationThreadPool-1, data: 4
     * threadName: RxComputationThreadPool-1 completed!
     */
    fun executeTakeLast() {
        val label = "take_last"
        val flowable: Flowable<Long> =
            Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .take(5)
                .takeLast(2)

        flowable.subscribe(DebugSubscriber(label = label))
        Thread.sleep(2000L)
    }
}