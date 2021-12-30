package operators

import io.reactivex.rxjava3.core.Flowable
import subscribers.DebugSubscriber

class DistinctUntilChangedOperatorExample {

    /**
     * [Result]
     * threadName: main, data: A
     * threadName: main, data: a
     * threadName: main, data: A
     * threadName: main, data: a
     * threadName: main completed!
     */
    fun executeDistinctUntilChanged() {
        val label = "distinct_until_changed"
        val flowable: Flowable<String> =
            Flowable.just("A", "a", "a", "A", "a")
                .distinctUntilChanged()

        flowable.subscribe(DebugSubscriber(label = label))
    }
}