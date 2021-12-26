package operators

import io.reactivex.rxjava3.core.Flowable
import subscribers.DebugSubscriber
import java.util.concurrent.TimeUnit

class ConcatMapEagerOperatorExample {

    /**
     * [Result]
     * 受け取ったデータから生成されるFlowableはすぐに実行されるが、通知するデータはconcatMapと同様で生成されたFlowableの順となる
     * threadName: RxComputationThreadPool-1, data: 1640442580247: [1] 0
     * threadName: RxComputationThreadPool-1, data: 1640442580344: [1] 1
     * threadName: RxComputationThreadPool-1, data: 1640442580446: [1] 2
     * threadName: RxComputationThreadPool-1, data: 1640442580249: [2] 0
     * threadName: RxComputationThreadPool-1, data: 1640442580344: [2] 1
     * threadName: RxComputationThreadPool-2, data: 1640442580448: [2] 2
     * threadName: RxComputationThreadPool-2, data: 1640442580249: [3] 0
     * threadName: RxComputationThreadPool-2, data: 1640442580344: [3] 1
     * threadName: RxComputationThreadPool-2, data: 1640442580448: [3] 2
     */
    fun executeConcatMapEager() {
        val label = "concat_map_eager"
        val flowable: Flowable<String> =
            Flowable.range(1, 3)
                .concatMapEager { sourceData ->
                    Flowable.interval(100L, TimeUnit.MILLISECONDS).take(3).map { data ->
                        val time = System.currentTimeMillis()
                        "$time: [$sourceData] $data"
                    }
                }

        flowable.subscribe(DebugSubscriber(label = label))
        Thread.sleep(1000L)
    }
}