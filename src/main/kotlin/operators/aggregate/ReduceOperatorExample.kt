package operators.aggregate

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import subscribers.DebugSingleObserver

class ReduceOperatorExample {

    /**
     * [Result]
     * threadName: main, data: 11111
     */
    fun executeReduce() {
        val label = "reduce"
        val single: Single<Long> =
            Flowable.just(1L, 10L, 100L, 1000L, 10000L)
                .reduce(0L, { sum, data -> sum + data })

        single.subscribe(DebugSingleObserver(label = label))
        Thread.sleep(2000L)
    }
}