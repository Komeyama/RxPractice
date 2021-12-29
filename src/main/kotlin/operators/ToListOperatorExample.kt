package operators

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import subscribers.DebugSingleObserver

class ToListOperatorExample {

    fun executeToList() {
        val label = "to_list"
        val single: Single<List<String>> =
            Flowable.just("A", "B", "C", "D", "E")
                .toList()

        single.subscribe(DebugSingleObserver(label = label))
    }
}