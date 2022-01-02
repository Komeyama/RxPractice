package operators.conversion

import io.reactivex.rxjava3.core.Flowable
import subscribers.DebugSubscriber
import java.util.concurrent.TimeUnit

class FlatMapOperatorExample {

    /**
     * [Result]
     * threadName: main, data: a
     * threadName: main, data: b
     * threadName: main, data: c
     * threadName: main completed!
     */
    fun executeFlatMapType1() {
        val label = "flat_map_type1"
        val flowableJust: Flowable<String> = Flowable.just("A", "", "B", "", "C").flatMap {
            if ("".equals(it)) {
                Flowable.empty()
            } else {
                Flowable.just(it.toLowerCase())
            }
        }

        flowableJust.subscribe(DebugSubscriber(label = label))
    }

    /**
     * [Result]
     * threadName: RxComputationThreadPool-1, data: 1640422048239: [1] 0
     * threadName: RxComputationThreadPool-3, data: 1640422048239: [3] 0
     * threadName: RxComputationThreadPool-2, data: 1640422048239: [2] 0
     * threadName: RxComputationThreadPool-1, data: 1640422048339: [1] 1
     * threadName: RxComputationThreadPool-1, data: 1640422048339: [2] 1
     * threadName: RxComputationThreadPool-1, data: 1640422048339: [3] 1
     * threadName: RxComputationThreadPool-1, data: 1640422048438: [1] 2
     * threadName: RxComputationThreadPool-3, data: 1640422048439: [3] 2
     * threadName: RxComputationThreadPool-3, data: 1640422048439: [2] 2
     */
    fun executeFlatMapType2() {
        val label = "flat_map_type2"
        val flowableJust: Flowable<String> =
            Flowable.range(1, 3)
                .flatMap(
                    { Flowable.interval(100L, TimeUnit.MILLISECONDS).take(3) }
                ) { sourceData, newData ->
                    val time = System.currentTimeMillis()
                    "$time: [$sourceData] $newData"
                }

        flowableJust.subscribe(DebugSubscriber(label = label))
        Thread.sleep(1000L)
    }

    /**
     * threadName: main, data: 10
     * threadName: main, data: 5
     * threadName: main, data: -1
     * threadName: main completed!
     */
    fun executeFlatMapType3() {
        val label = "flat_map_type3"
        val flowableJust: Flowable<Int> = Flowable.just(1, 2, 0, 4, 5).map { data -> 10 / data }.flatMap(
            { data -> Flowable.just(data) }, // data
            { Flowable.just(-1) }, // error
            // { error -> Flowable.error(Throwable("error!: $error")) }, // error
            { Flowable.just(100) } // complete
        )

        flowableJust.subscribe(DebugSubscriber(label = label))
    }
}