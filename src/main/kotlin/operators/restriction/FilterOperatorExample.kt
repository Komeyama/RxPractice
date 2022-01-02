package operators.restriction

import io.reactivex.rxjava3.core.Flowable
import subscribers.DebugSubscriber
import java.util.concurrent.TimeUnit

class FilterOperatorExample {

    /**
     * [Result]
     * threadName: RxComputationThreadPool-1, data: 0
     * threadName: RxComputationThreadPool-1, data: 2
     * threadName: RxComputationThreadPool-1, data: 4
     * threadName: RxComputationThreadPool-1, data: 6
     * threadName: RxComputationThreadPool-1, data: 8
     */
    fun executeFilter() {
        val label = "filter"
        val flowable: Flowable<Long> =
            Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .filter { data ->
                    data % 2L == 0L
                }

        flowable.subscribe(DebugSubscriber(label = label))
        Thread.sleep(3000L)
    }
}