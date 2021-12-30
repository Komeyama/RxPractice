package operators

import io.reactivex.rxjava3.core.Flowable
import subscribers.DebugSubscriber
import java.util.concurrent.TimeUnit

class TakeUntilOperatorExample {


    /**
     * [Result]
     * RxComputationThreadPool-1, data: 0
     * RxComputationThreadPool-1, data: 1
     * RxComputationThreadPool-1, data: 2
     * RxComputationThreadPool-1, data: 3
     * RxComputationThreadPool-1 completed!
     */
    fun executeTakeUntilType1() {
        val label = "take_until_type1"
        val flowable: Flowable<Long> =
            Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .takeUntil { data -> data == 3L }

        flowable.subscribe(DebugSubscriber(label = label))
        Thread.sleep(2000L)
    }

    /**
     * [Result]
     * RxComputationThreadPool-3, data: 0
     * RxComputationThreadPool-3, data: 1
     * RxComputationThreadPool-3, data: 2
     * RxComputationThreadPool-2 completed!
     */
    fun executeTakeUntilType2() {
        val label = "take_until_type2"
        val flowable: Flowable<Long> =
            Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .takeUntil(Flowable.timer(1000L, TimeUnit.MILLISECONDS))

        flowable.subscribe(DebugSubscriber(label = label))
        Thread.sleep(2000L)
    }
}