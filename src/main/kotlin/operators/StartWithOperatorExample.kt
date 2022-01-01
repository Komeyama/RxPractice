package operators

import io.reactivex.rxjava3.core.Flowable
import subscribers.DebugSubscriber
import java.util.concurrent.TimeUnit

class StartWithOperatorExample {

    /**
     * [Result]
     * threadName: RxComputationThreadPool-1, data: 100
     * threadName: RxComputationThreadPool-1, data: 101
     * threadName: RxComputationThreadPool-2, data: 0
     * threadName: RxComputationThreadPool-2, data: 1
     * threadName: RxComputationThreadPool-2, data: 2
     * threadName: RxComputationThreadPool-2, data: 3
     * threadName: RxComputationThreadPool-2, data: 4
     * threadName: RxComputationThreadPool-2 completed!
     * 先に引数のデータを通知する
     */
    fun executeStartWith() {
        val label = "start_with"
        val flowable: Flowable<Long> = flowable1().startWith(flowable2())

        flowable.subscribe(DebugSubscriber(label = label))
        Thread.sleep(3000L)
    }

    private fun flowable1(): Flowable<Long> {
        return Flowable
            .interval(300L, TimeUnit.MILLISECONDS)
            .take(5)
    }

    private fun flowable2(): Flowable<Long> {
        return Flowable
            .interval(500L, TimeUnit.MILLISECONDS)
            .take(2)
            .map { data -> data + 100L }
    }
}