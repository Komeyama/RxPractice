package operators.conversion

import io.reactivex.rxjava3.core.Flowable
import subscribers.DebugSubscriber
import java.util.concurrent.TimeUnit

class BufferOperatorExample {

    /**
     * [Result]
     * threadName: RxComputationThreadPool-1, data: [0, 1, 2]
     * threadName: RxComputationThreadPool-1, data: [3, 4, 5]
     * threadName: RxComputationThreadPool-1, data: [6, 7, 8]
     * threadName: RxComputationThreadPool-1, data: [9]
     * threadName: RxComputationThreadPool-1 completed!
     */
    fun executeBufferType1() {
        val label = "buffer_type1"
        val flowable: Flowable<List<Long>> =
            Flowable.interval(100L, TimeUnit.MILLISECONDS)
                .take(10)
                .buffer(3)

        flowable.subscribe(DebugSubscriber(label = label))
        Thread.sleep(1000L)
    }

    /**
     * [Result]
     * threadName: RxComputationThreadPool-1, data: [0, 1, 2]
     * threadName: RxComputationThreadPool-3, data: [3, 4, 5]
     * threadName: RxComputationThreadPool-2, data: [6, 7, 8, 9]
     * threadName: RxComputationThreadPool-2 completed!
     *
     *     0    1    2    3    4    5    6    7    8    9
     * ----|----|----|----|----|----|----|----|----|----|----|----|----|----> [ms]
     *
     * 0  300  600  900  1200 1500 1800 2100 2400 2700 3000 3300 3600 3900
     *
     * -----------------|--------------|----------------|----------------|--> [ms]
     *                1000           2000             3000
     */
    fun executeBufferType2() {
        val label = "buffer_type2"
        val flowable: Flowable<List<Long>> =
            Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .take(10)
                .buffer(Flowable.timer(1000L, TimeUnit.MILLISECONDS).repeat())

        flowable.subscribe(DebugSubscriber(label = label))
        Thread.sleep(4000L)
    }
}