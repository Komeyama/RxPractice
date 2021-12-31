package operators

import io.reactivex.rxjava3.core.Flowable
import subscribers.DebugSubscriber
import java.util.concurrent.TimeUnit

class ThrottleLastOperatorExample {

    /**
     * [Result]
     * threadName: RxComputationThreadPool-1, data: 2
     * threadName: RxComputationThreadPool-1, data: 5
     * threadName: RxComputationThreadPool-1, data: 8
     * threadName: RxComputationThreadPool-1 completed!
     *
     *     0    1    2    3    4    5    6    7    8    9
     * ----|----|----|----|----|----|----|----|----|----|----|----|----|----> [ms]
     *
     * 0  300  600  900  1200 1500 1800 2100 2400 2700 3000 3300 3600 3900
     *
     * |----------------|
     *  <--- 1000ms --->
     *               |----------------|
     *                <--- 1000ms --->
     *                              |----------------|
     *                               <--- 1000ms --->
     */
    fun executeThrottleLast() {
        val label = "throttle_last"
        val flowable: Flowable<Long> =
            Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .take(10)
                .throttleLast(1000L, TimeUnit.MILLISECONDS)

        flowable.subscribe(DebugSubscriber(label = label))
        Thread.sleep(4000L)
    }
}