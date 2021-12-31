package operators

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Maybe
import subscribers.DebugMaybeObserver
import java.util.concurrent.TimeUnit

class ElementAtOperatorExample {

    /**
     * [Result]
     * threadName: RxComputationThreadPool-1, data: 3
     */
    fun executeElementAt() {
        val label = "telement_at"
        val maybe: Maybe<Long> =
            Flowable.interval(500L, TimeUnit.MILLISECONDS)
                .elementAt(3)

        maybe.subscribe(DebugMaybeObserver(label = label))
        Thread.sleep(3000L)
    }
}