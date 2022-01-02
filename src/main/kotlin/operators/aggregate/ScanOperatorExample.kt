package operators.aggregate

import io.reactivex.rxjava3.core.Flowable
import subscribers.DebugSubscriber

class ScanOperatorExample {

    /**
     * [Result]
     * threadName: main, data: 11111
     */
    fun executeScan() {
        val label = "scan"
        val single: Flowable<Long> =
            Flowable.just(1L, 10L, 100L, 1000L, 10000L)
                .scan(0L, { sum, data -> sum + data })

        single.subscribe(DebugSubscriber(label = label))
        Thread.sleep(2000L)
    }
}