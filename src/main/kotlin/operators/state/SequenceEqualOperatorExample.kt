package operators.state

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import subscribers.DebugSingleObserver
import java.util.concurrent.TimeUnit

class SequenceEqualOperatorExample {

    /**
     * [Result]
     * threadName: RxComputationThreadPool-1, data: true
     * 同じデータを同じ順で同じ数だけ持っているのかを判定
     */
    fun executeSequenceEqual() {
        val label = "sequence_equal"

        val flowable1: Flowable<Long> =
            Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .take(3)
        val flowable2: Flowable<Long> =
            Flowable.just(0L, 1L, 2L)
        val single: Single<Boolean> = Flowable.sequenceEqual(flowable1, flowable2)

        single.subscribe(DebugSingleObserver(label = label))
        Thread.sleep(2000L)
    }
}