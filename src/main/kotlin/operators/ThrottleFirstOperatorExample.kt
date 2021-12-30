package operators

import io.reactivex.rxjava3.core.Flowable
import subscribers.DebugSubscriber
import java.util.concurrent.TimeUnit

class ThrottleFirstOperatorExample {

    /**
     * [Result]
     * threadName: RxComputationThreadPool-1, data: 0
     * threadName: RxComputationThreadPool-1, data: 4
     * threadName: RxComputationThreadPool-1, data: 8
     * threadName: RxComputationThreadPool-1 completed!
     *
     *     0    1    2    3    4    5    6    7    8    9
     * ----|----|----|----|----|----|----|----|----|----|----|----|----|----> [ms]
     *
     * 0  300  600  900  1200 1500 1800 2100 2400 2700 3000 3300 3600 3900
     *
     * ----|----------------|--|----------------|--|----------------|--
     *      <--- 1000ms --->    <--- 1000ms --->    <--- 1000ms --->
     */
    fun executeThrottleFirst() {
        val label = "throttle_first"
        val flowable: Flowable<Long> =
            Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .take(10)
                .throttleFirst(1000L, TimeUnit.MILLISECONDS)

        flowable.subscribe(DebugSubscriber(label = label))
        Thread.sleep(4000L)
    }
}