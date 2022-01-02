package operators.generation

import io.reactivex.rxjava3.core.Flowable
import subscribers.DebugSubscriber


class RangeOperatorExample {

    /***
     * [Result]
     * 10
     * 11
     * 12
     * completed!
     */
    fun executeRange() {
        val flowable: Flowable<Int> = Flowable.range(10, 3)
        flowable.subscribe(DebugSubscriber(label = "range"))
    }
}