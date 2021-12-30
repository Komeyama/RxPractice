package operators

import io.reactivex.rxjava3.core.Flowable
import subscribers.DebugSubscriber
import java.math.BigDecimal

class DistinctUntilChangedOperatorExample {

    /**
     * [Result]
     * threadName: main, data: A
     * threadName: main, data: a
     * threadName: main, data: A
     * threadName: main, data: a
     * threadName: main completed!
     */
    fun executeDistinctUntilChangedType1() {
        val label = "distinct_until_changed_type1"
        val flowable: Flowable<String> =
            Flowable.just("A", "a", "a", "A", "a")
                .distinctUntilChanged()

        flowable.subscribe(DebugSubscriber(label = label))
    }

    /**
     * [Result]
     * threadName: main, data: 1
     * threadName: main, data: 0.1
     * threadName: main, data: 1
     * threadName: main completed!
     */
    fun executeDistinctUntilChangedType2() {
        val label = "distinct_until_changed_type2"
        val flowable: Flowable<String> =
            Flowable.just("1", "1.0", "0.1", "0.10", "1")
                .distinctUntilChanged { data1, data2 ->
                    val bigDecimal1 = BigDecimal(data1)
                    val bigDecimal2 = BigDecimal(data2)
                    bigDecimal1.compareTo(bigDecimal2) == 0
                }

        flowable.subscribe(DebugSubscriber(label = label))
    }
}