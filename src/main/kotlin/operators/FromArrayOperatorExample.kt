package operators

import io.reactivex.rxjava3.core.Flowable
import subscribers.DebugSubscriber

class FromArrayOperatorExample {

    /***
     * [Result]
     * A
     * B
     * C
     * D
     * E
     * completed!
     */
    fun executeFromArray() {
        val flowable: Flowable<String> = Flowable.fromArray("A", "B", "C", "D", "E")
        flowable.subscribe(DebugSubscriber(label = "from_array"))
    }
}