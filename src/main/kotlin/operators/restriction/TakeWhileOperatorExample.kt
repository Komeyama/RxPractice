package operators.restriction

import io.reactivex.rxjava3.core.Flowable
import subscribers.DebugSubscriber
import java.util.concurrent.TimeUnit

class TakeWhileOperatorExample {

    /**
     * [Result]
     * RxComputationThreadPool-1, data: 0
     * RxComputationThreadPool-1, data: 1
     * RxComputationThreadPool-1 completed!
     */
    fun executeTakeWhile() {
        val label = "take_while"
        val flowable: Flowable<Long> =
            Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .takeWhile { data -> data != 2L }

        flowable.subscribe(DebugSubscriber(label = label))
        Thread.sleep(2000L)
    }
}