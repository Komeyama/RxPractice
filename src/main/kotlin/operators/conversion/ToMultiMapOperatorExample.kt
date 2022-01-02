package operators.conversion

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import subscribers.DebugSingleObserver
import java.util.concurrent.TimeUnit

class ToMultiMapOperatorExample {

    /**
     * [Result]
     * threadName: RxComputationThreadPool-1, data: {even=[0, 2, 4], odd=[1, 3]}
     */
    fun executeToMultiMapOperator() {
        val label = "to_multi_map"
        val single: Single<Map<String, Collection<Long>>> =
            Flowable.interval(500L, TimeUnit.MILLISECONDS)
                .take(5)
                .toMultimap { data ->
                    if (data % 2L == 0L) {
                        "even"
                    } else {
                        "odd"
                    }
                }

        single.subscribe(DebugSingleObserver(label = label))
        Thread.sleep(3000L)
    }
}