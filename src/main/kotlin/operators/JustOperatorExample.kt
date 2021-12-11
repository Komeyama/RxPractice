package operators

import io.reactivex.rxjava3.core.Flowable
import subscribers.DebugSubscriber

class JustOperatorExample {

    fun execute() {
        val flowable: Flowable<String> = Flowable.just("A", "B", "C", "D", "E")
        flowable.subscribe(DebugSubscriber())
    }
}