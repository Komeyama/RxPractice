package operators

import io.reactivex.rxjava3.core.Flowable
import subscribers.DebugTimeSubscriber
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit

class ConcatEagerOperatorExample {

    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("mm:ss.SSS")

    /**
     * [Result]
     * threadName: RxComputationThreadPool-1, time: 51:53.884, data: 0
     * threadName: RxComputationThreadPool-1, time: 51:54.166, data: 1
     * threadName: RxComputationThreadPool-1, time: 51:54.466, data: 2
     * threadName: RxComputationThreadPool-1, time: 51:54.768, data: 3
     * threadName: RxComputationThreadPool-1, time: 51:55.068, data: 4
     * threadName: RxComputationThreadPool-1, time: 51:55.069, data: 100
     * threadName: RxComputationThreadPool-1, time: 51:55.069, data: 101
     * threadName: RxComputationThreadPool-1, time: 51:55.070, data: 102
     * threadName: RxComputationThreadPool-2, time: 51:55.568, data: 103
     * threadName: RxComputationThreadPool-2, time: 51:56.072, data: 104
     * threadName: RxComputationThreadPool-2, time: 51:56.073, completed!
     * 第一引数、第二引数・・・の順に通知するが、処理の開始は等しいため第一引数の終了までに実行されていた処理は終了と同時に全て実行される
     */
    fun executeConcatEager() {
        val label = "concat_eager"
        val sourecs = Arrays.asList(flowable1(), flowable2())
        val flowable: Flowable<Long> =
            Flowable.concatEager(sourecs)

        flowable.subscribe(DebugTimeSubscriber(formatter = formatter, label = label))
        Thread.sleep(3000L)
    }

    private fun flowable1(): Flowable<Long> {
        return Flowable
            .interval(300L, TimeUnit.MILLISECONDS)
            .take(5)
    }

    private fun flowable2(): Flowable<Long> {
        return Flowable
            .interval(500L, TimeUnit.MILLISECONDS)
            .take(5)
            .map { data -> data + 100L }
    }
}