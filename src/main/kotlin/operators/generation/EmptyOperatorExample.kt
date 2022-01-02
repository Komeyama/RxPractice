package operators.generation

import io.reactivex.rxjava3.core.Flowable
import subscribers.DebugSubscriber

class EmptyOperatorExample {

    /**
     * [Result]
     * threadName: main completed!
     */
    fun executeEmpty() {
        val label = "empty"
        val flowable: Flowable<Long> = Flowable.empty()
        flowable.subscribe(DebugSubscriber(label = label))
    }
}