package operators.conversion

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import subscribers.DebugSingleObserver

class ToListOperatorExample {

    /**
     * [Result]
     * threadName: main, data: [A, B, C, D, E]
     */
    fun executeToList() {
        val label = "to_list"
        val single: Single<List<String>> =
            Flowable.just("A", "B", "C", "D", "E")
                .toList()

        single.subscribe(DebugSingleObserver(label = label))
    }
}