package operators.restriction

import io.reactivex.rxjava3.core.Flowable
import subscribers.DebugSubscriber

class DistinctOperatorExample {

    /**
     * [Result]
     * threadName: main, data: Hoge(id=0, name=hoge1)
     * threadName: main, data: Hoge(id=1, name=hoge2)
     * threadName: main, data: Hoge(id=0, name=hoge1_2)
     * threadName: main, data: Hoge(id=2, name=hoge1)
     * threadName: main completed!
     */
    fun executeDistinct() {
        val label = "distinct"
        val flowable: Flowable<Hoge> =
            Flowable.just(
                Hoge(id = 0, name = "hoge1"),
                Hoge(id = 1, name = "hoge2"),
                Hoge(id = 0, name = "hoge1"), // Duplicate!
                Hoge(id = 0, name = "hoge1_2"),
                Hoge(id = 2, name = "hoge1")
            ).distinct()

        flowable.subscribe(DebugSubscriber(label = label))
    }

    data class Hoge(val id: Int, val name: String)
}