package operators.restriction

import io.reactivex.rxjava3.core.Flowable
import subscribers.DebugSubscriber
import java.util.concurrent.TimeUnit

class SkipWhileOperatorExample {

    /**
     * [Result]
     * threadName: RxComputationThreadPool-1, data: 3
     * threadName: RxComputationThreadPool-1, data: 4
     * threadName: RxComputationThreadPool-1, data: 5
     */
    fun executeSkipWhile() {
        val label = "skip_while"
        val flowable: Flowable<Long> =
            Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .skipWhile { data -> data != 3L }

        flowable.subscribe(DebugSubscriber(label = label))
        Thread.sleep(2000L)
    }
}