package operators.combine

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.functions.Function3
import io.reactivex.rxjava3.functions.Function4
import subscribers.DebugTimeSubscriber
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

class CombineLatestOperatorExample {

    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("mm:ss.SSS")

    /**
     * [Result]
     * threadName: RxComputationThreadPool-2, time: 25:43.108, data: [0, 100]
     * threadName: RxComputationThreadPool-1, time: 25:43.225, data: [1, 100]
     * threadName: RxComputationThreadPool-2, time: 25:43.427, data: [1, 101]
     * threadName: RxComputationThreadPool-1, time: 25:43.526, data: [2, 101]
     * threadName: RxComputationThreadPool-1, time: 25:43.828, data: [3, 101]
     * threadName: RxComputationThreadPool-1, time: 25:43.828, data: [3, 102]
     * threadName: RxComputationThreadPool-1, time: 25:44.125, data: [4, 102]
     * threadName: RxComputationThreadPool-1, time: 25:44.127, completed!
     */
    fun executeCombineLatestType1() {
        val label = "combine_latest_type1"
        val flowable: Flowable<List<Long>> =
            Flowable.combineLatest(
                flowable1(),
                flowable2(),
                BiFunction { data1, data2
                    ->
                    listOf(data1, data2)
                }
            )

        flowable.subscribe(DebugTimeSubscriber(formatter = formatter, label = label))
        Thread.sleep(3000L)
    }

    /**
     * [Result]
     * threadName: RxComputationThreadPool-1, time: 25:46.139, data: (0, 100, 1000)
     * threadName: RxComputationThreadPool-3, time: 25:46.243, data: (1, 100, 1000)
     * threadName: RxComputationThreadPool-4, time: 25:46.441, data: (1, 101, 1000)
     * threadName: RxComputationThreadPool-3, time: 25:46.539, data: (2, 101, 1000)
     * threadName: RxComputationThreadPool-1, time: 25:46.642, data: (2, 101, 1001)
     * threadName: RxComputationThreadPool-3, time: 25:46.841, data: (3, 101, 1001)
     * threadName: RxComputationThreadPool-3, time: 25:46.841, data: (3, 102, 1001)
     * threadName: RxComputationThreadPool-3, time: 25:47.143, data: (4, 102, 1001)
     * threadName: RxComputationThreadPool-3, time: 25:47.143, data: (4, 102, 1002)
     * threadName: RxComputationThreadPool-3, time: 25:47.143, completed!
     */
    fun executeCombineLatestType2() {
        val label = "combine_latest_typ2"
        val flowable: Flowable<Triple<Long, Long, Long>>? =
            Flowable.combineLatest(
                flowable1(),
                flowable2(),
                flowable3(),
                Function3<Long, Long, Long, Triple<Long, Long, Long>> { n1, n2, n3
                    ->
                    Triple(n1, n2, n3)
                }
            )

        flowable?.subscribe(DebugTimeSubscriber(formatter = formatter, label = label))
        Thread.sleep(3000L)
    }

    /**
     * [Result]
     * threadName: RxComputationThreadPool-1, time: 31:30.674, data: Quadruple(n1=1, n2=100, n3=1000, n4=10000)
     * threadName: RxComputationThreadPool-3, time: 31:30.872, data: Quadruple(n1=1, n2=101, n3=1000, n4=10000)
     * threadName: RxComputationThreadPool-2, time: 31:30.972, data: Quadruple(n1=2, n2=101, n3=1000, n4=10000)
     * threadName: RxComputationThreadPool-4, time: 31:31.077, data: Quadruple(n1=2, n2=101, n3=1001, n4=10000)
     * threadName: RxComputationThreadPool-2, time: 31:31.274, data: Quadruple(n1=3, n2=101, n3=1001, n4=10000)
     * threadName: RxComputationThreadPool-2, time: 31:31.274, data: Quadruple(n1=3, n2=101, n3=1001, n4=10001)
     * threadName: RxComputationThreadPool-2, time: 31:31.275, data: Quadruple(n1=3, n2=102, n3=1001, n4=10001)
     * threadName: RxComputationThreadPool-4, time: 31:31.576, data: Quadruple(n1=3, n2=102, n3=1002, n4=10001)
     * threadName: RxComputationThreadPool-4, time: 31:31.577, data: Quadruple(n1=4, n2=102, n3=1002, n4=10001)
     * threadName: RxComputationThreadPool-1, time: 31:31.872, data: Quadruple(n1=4, n2=102, n3=1002, n4=10002)
     * threadName: RxComputationThreadPool-1, time: 31:31.873, completed!
     */
    fun executeCombineLatestType3() {
        val label = "combine_latest_typ3"
        val flowable: Flowable<Quadruple>? =
            Flowable.combineLatest(
                flowable1(),
                flowable2(),
                flowable3(),
                flowable4(),
                Function4<Long, Long, Long, Long, Quadruple> { n1, n2, n3, n4
                    ->
                    Quadruple(n1, n2, n3, n4)
                }
            )

        flowable?.subscribe(DebugTimeSubscriber(formatter = formatter, label = label))
        Thread.sleep(3000L)
    }

    data class Quadruple(val n1: Long, val n2: Long, val n3: Long, val n4: Long)

    private fun flowable1(): Flowable<Long> {
        return Flowable
            .interval(300L, TimeUnit.MILLISECONDS)
            .take(5)
    }

    private fun flowable2(): Flowable<Long> {
        return Flowable
            .interval(400L, TimeUnit.MILLISECONDS)
            .take(3)
            .map { data -> data + 100L }
    }

    private fun flowable3(): Flowable<Long> {
        return Flowable
            .interval(500L, TimeUnit.MILLISECONDS)
            .take(3)
            .map { data -> data + 1000L }
    }

    private fun flowable4(): Flowable<Long> {
        return Flowable
            .interval(600L, TimeUnit.MILLISECONDS)
            .take(3)
            .map { data -> data + 10000L }
    }
}