package operators

import io.reactivex.rxjava3.core.Flowable
import subscribers.DebugSubscriber
import java.time.LocalTime

class DeferOperatorExample {

    /**
     * [Result]
     * threadName: main, data: 23:33:33.920
     * threadName: main completed!
     * threadName: main, data: 23:33:35.923
     * threadName: main completed!
     */
    fun executeDefer() {
        val label = "defer"
        val flowable: Flowable<LocalTime> = Flowable.defer { Flowable.just(LocalTime.now()) }
        flowable.subscribe(DebugSubscriber(label = label))
        Thread.sleep(2000L)
        flowable.subscribe(DebugSubscriber(label = label))
    }

}