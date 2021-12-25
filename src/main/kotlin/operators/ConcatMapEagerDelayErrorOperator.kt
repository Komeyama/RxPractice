package operators

import io.reactivex.rxjava3.core.Flowable
import subscribers.DebugSubscriber
import java.lang.Exception
import java.util.concurrent.TimeUnit

class ConcatMapEagerDelayErrorOperator {

    /**
     * [Result]
     * isDelayError = falseの場合
     * threadName: RxComputationThreadPool-1, data: 1640444633921: [1] 0
     * threadName: RxComputationThreadPool-3, data: 1640444634024: [1] 1
     * threadName: RxComputationThreadPool-1, data: 1640444634124: [1] 2
     * threadName: RxComputationThreadPool-1, throwable: java.lang.Exception: concat error!
     * isDelayError = trueの場合
     * threadName: RxComputationThreadPool-1, data: 1640444709908: [1] 0
     * threadName: RxComputationThreadPool-1, data: 1640444710006: [1] 1
     * threadName: RxComputationThreadPool-1, data: 1640444710104: [1] 2
     * threadName: RxComputationThreadPool-1, data: 1640444709908: [2] 0
     * threadName: RxComputationThreadPool-1, data: 1640444709908: [3] 0
     * threadName: RxComputationThreadPool-1, data: 1640444710006: [3] 1
     * threadName: RxComputationThreadPool-3, data: 1640444710108: [3] 2
     * threadName: RxComputationThreadPool-3, throwable: java.lang.Exception: concat error!
     */
    fun executeConcatMapEagerDelayError(isDelayError: Boolean = true) {
        val label = "concat_map_eager_delay_error_delay"
        val flowableJust: Flowable<String> =
            Flowable.range(1, 3)
                .concatMapEagerDelayError({ sourceData ->
                    Flowable.interval(100L, TimeUnit.MILLISECONDS).take(3).doOnNext { data ->
                        if (sourceData == 2 && data == 2L) {
                            throw Exception("concat map eager error!")
                        }
                    }.map { data ->
                        val time = System.currentTimeMillis()
                        "$time: [$sourceData] $data"
                    }
                }, isDelayError)

        flowableJust.subscribe(DebugSubscriber(label = label))
        Thread.sleep(1000L)
    }
}