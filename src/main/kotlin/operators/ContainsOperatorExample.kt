package operators

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import subscribers.DebugSingleObserver
import subscribers.DebugSubscriber
import java.util.concurrent.TimeUnit

class ContainsOperatorExample {

    /**
     * [Result]
     * threadName: RxComputationThreadPool-1, data: 3
     * threadName: RxComputationThreadPool-1 completed!
     * threadName: RxComputationThreadPool-2, data: true
     */
    fun executeContains() {
        val label = "contains"

        val flowable: Flowable<Long> = Flowable.interval(300L, TimeUnit.MILLISECONDS)
            .take(4)
            .filter { data -> data >= 3 }
        val single: Single<Boolean> = flowable.contains(3L)

        flowable.subscribe(DebugSubscriber(label = label))
        single.subscribe(DebugSingleObserver(label = label))
        Thread.sleep(2000L)
    }
}