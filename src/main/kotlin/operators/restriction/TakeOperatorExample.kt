package operators.restriction

import io.reactivex.rxjava3.core.Flowable
import subscribers.DebugSubscriber
import java.util.concurrent.TimeUnit

class TakeOperatorExample {

    /**
     * [Result]
     * RxComputationThreadPool-1, data: 0
     * RxComputationThreadPool-1, data: 1
     * RxComputationThreadPool-1, data: 2
     * RxComputationThreadPool-1 completed!
     */
    fun executeTake() {
        val label = "take"
        val flowable: Flowable<Long> =
            Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .take(3)

        flowable.subscribe(DebugSubscriber(label = label))
        Thread.sleep(1000L)
    }
}