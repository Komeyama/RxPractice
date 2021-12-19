package operators

import io.reactivex.rxjava3.core.Flowable
import subscribers.DebugSubscriber

class MapOperatorExample {

    /**
     * [Result]
     * threadName: main, data: a
     * threadName: main, data: b
     * threadName: main, data: c
     * threadName: main, data: d
     * threadName: main, data: e
     * threadName: main completed!
     */
    fun executeMap() {
        val label = "map"
        val flowableJust = Flowable.just("A", "B", "C", "D", "E").map {
            it.toLowerCase()
        }

        flowableJust.subscribe(DebugSubscriber(label = label))
    }
}