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
    fun executeTakeLastType1() {
        val label = "take_last_type1"
        val flowable: Flowable<Long> =
            Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .take(5)
                .takeLast(2)

        flowable.subscribe(DebugSubscriber(label = label))
        Thread.sleep(2000L)
    }

    /**
     * [Result]
     * threadName: RxComputationThreadPool-2, data: 6
     * threadName: RxComputationThreadPool-2, data: 7
     * threadName: RxComputationThreadPool-2 completed!
     */
    fun executeTakeLastType2() {
        val label = "take_last_type2"
        val flowable: Flowable<Long> =
            Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .take(8)
                .takeLast(2, 1000L, TimeUnit.MILLISECONDS)

        flowable.subscribe(DebugSubscriber(label = label))
        Thread.sleep(3000L)
    }
}