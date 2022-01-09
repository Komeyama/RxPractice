package operators.utility

import io.reactivex.rxjava3.core.Flowable
import subscribers.DebugTimeSubscriber
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

class RepeatWhenOperatorExample {

    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("mm:ss.SSS")

    /**
     * [Result]
     * threadName: main, time: 50:01.587, data: 1
     * threadName: main, time: 50:01.591, data: 2
     * threadName: main, time: 50:01.591, data: 3
     * emit: 0
     * threadName: RxComputationThreadPool-1, time: 50:02.596, data: 1
     * threadName: RxComputationThreadPool-1, time: 50:02.596, data: 2
     * threadName: RxComputationThreadPool-1, time: 50:02.596, data: 3
     * emit: 0
     * threadName: RxComputationThreadPool-1, time: 50:03.599, data: 1
     * threadName: RxComputationThreadPool-1, time: 50:03.599, data: 2
     * threadName: RxComputationThreadPool-1, time: 50:03.599, data: 3
     * repeat complete!
     * threadName: RxComputationThreadPool-1, time: 50:03.600, completed!
     */
    fun executeRepeatWhen() {
        val label = "repeat_when"
        val flowable: Flowable<Long> =
            Flowable.just(1L, 2L, 3L)
                .repeatWhen {
                    it.delay(1000L, TimeUnit.MILLISECONDS)
                        .take(2)
                        .doOnNext { data -> println("emit: $data") }
                        .doOnComplete { println("repeat complete!") }
                }

        flowable.subscribe(DebugTimeSubscriber(formatter = formatter, label = label))
        Thread.sleep(5000L)
    }
}