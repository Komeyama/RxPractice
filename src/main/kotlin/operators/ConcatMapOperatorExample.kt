package operators

import io.reactivex.rxjava3.core.Flowable
import subscribers.DebugSubscriber
import java.util.concurrent.TimeUnit

class ConcatMapOperatorExample {

    /**
     * [Result]
     * threadName: RxComputationThreadPool-1, data: 1640420863136: [1] 0
     * threadName: RxComputationThreadPool-1, data: 1640420863237: [1] 1
     * threadName: RxComputationThreadPool-1, data: 1640420863335: [1] 2
     * threadName: RxComputationThreadPool-2, data: 1640420863435: [2] 0
     * threadName: RxComputationThreadPool-2, data: 1640420863539: [2] 1
     * threadName: RxComputationThreadPool-2, data: 1640420863636: [2] 2
     * threadName: RxComputationThreadPool-3, data: 1640420863736: [3] 0
     * threadName: RxComputationThreadPool-3, data: 1640420863840: [3] 1
     * threadName: RxComputationThreadPool-3, data: 1640420863937: [3] 2
     */
    fun executeConcatMap() {
        val label = "concat_map"
        val flowable: Flowable<String> =
            Flowable.range(1, 3)
                .concatMap { sourceData ->
                    Flowable.interval(100L, TimeUnit.MILLISECONDS).take(3).map { data ->
                        val time = System.currentTimeMillis()
                        "$time: [$sourceData] $data"
                    }
                }

        flowable.subscribe(DebugSubscriber(label = label))
        Thread.sleep(1000L)
    }
}