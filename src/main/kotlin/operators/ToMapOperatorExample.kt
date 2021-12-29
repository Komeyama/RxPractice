package operators

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import subscribers.DebugSingleObserver

class ToMapOperatorExample {

    fun executeToMap() {
        val label = "to_map"
        val single: Single<Map<Int, String>> =
            Flowable.just("A", "B", "C", "D", "E")
                .toMap { data -> data.toCharArray()[0].toInt() }

        single.subscribe(DebugSingleObserver(label = label))
    }
}