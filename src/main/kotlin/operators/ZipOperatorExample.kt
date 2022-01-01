package operators

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.functions.Function3
import io.reactivex.rxjava3.functions.Function4
import subscribers.DebugTimeSubscriber
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

class ZipOperatorExample {

    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("mm:ss.SSS")

    /**
     * [Result]
     * threadName: RxComputationThreadPool-2, time: 40:04.047, data: [0, 100]
     * threadName: RxComputationThreadPool-2, time: 40:04.413, data: [1, 101]
     * threadName: RxComputationThreadPool-2, time: 40:04.812, data: [2, 102]
     * threadName: RxComputationThreadPool-2, time: 40:04.814, completed!
     */
    fun executeZipType1() {
        val label = "zip_type1"
        val flowable: Flowable<List<Long>> =
            Flowable.zip(
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
     * threadName: RxComputationThreadPool-1, time: 40:07.124, data: (0, 100, 1000)
     * threadName: RxComputationThreadPool-1, time: 40:07.618, data: (1, 101, 1001)
     * threadName: RxComputationThreadPool-3, time: 40:08.121, data: (2, 102, 1002)
     * threadName: RxComputationThreadPool-3, time: 40:08.121, completed!
     */
    fun executeZipType2() {
        val label = "zip_typ2"
        val flowable: Flowable<Triple<Long, Long, Long>>? =
            Flowable.zip(
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
     * threadName: RxComputationThreadPool-1, time: 40:10.232, data: Quadruple(n1=0, n2=100, n3=1000, n4=10000)
     * threadName: RxComputationThreadPool-1, time: 40:10.834, data: Quadruple(n1=1, n2=101, n3=1001, n4=10001)
     * threadName: RxComputationThreadPool-1, time: 40:11.434, data: Quadruple(n1=2, n2=102, n3=1002, n4=10002)
     * threadName: RxComputationThreadPool-1, time: 40:11.434, completed!
     */
    fun executeZipType3() {
        val label = "zip_typ3"
        val flowable: Flowable<Quadruple>? =
            Flowable.zip(
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