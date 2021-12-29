package operators

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import subscribers.DebugSingleObserver

class ToMapOperatorExample {

    /**
     * [Result]
     * threadName: main, data: {65=A, 66=B, 67=C, 68=D, 69=E}
     */
    fun executeToMap() {
        val label = "to_map"
        val single: Single<Map<Int, String>> =
            Flowable.just("A", "B", "C", "D", "E")
                .toMap { data -> data.toCharArray()[0].toInt() }

        single.subscribe(DebugSingleObserver(label = label))
    }
}