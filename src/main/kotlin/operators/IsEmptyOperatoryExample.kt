package operators

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import subscribers.DebugSingleObserver
import subscribers.DebugSubscriber
import java.util.concurrent.TimeUnit

class IsEmptyOperatoryExample {

    /**
     * [Result]
     * threadName: RxComputationThreadPool-1 completed!
     * threadName: RxComputationThreadPool-2, data: true
     */
    fun executeEmpty() {
        val label = "is_empty"

        val flowable: Flowable<Long> = Flowable.interval(300L, TimeUnit.MILLISECONDS)
            .take(3)
            .filter { data -> data >= 3 }
        val single: Single<Boolean> = flowable.isEmpty

        flowable.subscribe(DebugSubscriber(label = label))
        single.subscribe(DebugSingleObserver(label = label))
        Thread.sleep(2000L)
    }
}