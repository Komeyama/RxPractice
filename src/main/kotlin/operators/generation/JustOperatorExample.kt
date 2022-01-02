package operators.generation

import io.reactivex.rxjava3.core.Flowable
import subscribers.DebugSubscriber


class JustOperatorExample {

    /***
     * [Result]
     * A
     * B
     * C
     * D
     * E
     * completed!
     */
    fun executeJust() {
        val label = "just"
        val flowable: Flowable<String> = Flowable.just("A", "B", "C", "D", "E")
        flowable.subscribe(DebugSubscriber(label = label))
    }
}