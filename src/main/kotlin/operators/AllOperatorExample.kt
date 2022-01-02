package operators

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import subscribers.DebugSingleObserver
import subscribers.DebugSubscriber
import java.util.concurrent.TimeUnit

class AllOperatorExample {

    /**
     * [Result]
     * threadName: RxComputationThreadPool-1, data: 0
     * threadName: RxComputationThreadPool-1, data: 1
     * threadName: RxComputationThreadPool-1, data: 2
     * threadName: RxComputationThreadPool-1, data: 3
     * threadName: RxComputationThreadPool-1 completed!
     * threadName: RxComputationThreadPool-2, data: true
     */
    fun executeAll() {
        val label = "all"

        val flowable: Flowable<Long> = Flowable.interval(300L, TimeUnit.MILLISECONDS)
            .take(4)

        val single: Single<Boolean> = flowable.all { data -> data < 5 }

        flowable.subscribe(DebugSubscriber(label = label))
        single.subscribe(DebugSingleObserver(label = label))
        Thread.sleep(2000L)
    }
}