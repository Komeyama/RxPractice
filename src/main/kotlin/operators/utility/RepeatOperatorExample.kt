package operators.utility

import io.reactivex.rxjava3.core.Flowable
import subscribers.DebugSubscriber

class RepeatOperatorExample {

    /**
     * [Result]
     * threadName: main, data: A
     * threadName: main, data: B
     * threadName: main, data: C
     * threadName: main, data: A
     * threadName: main, data: B
     * threadName: main, data: C
     * threadName: main, data: A
     * threadName: main, data: B
     * threadName: main, data: C
     * threadName: main completed!
     */
    fun executeRepeat() {
        val label = "repeat"
        val single: Flowable<String> =
            Flowable.just("A", "B", "C")
                .repeat(3)

        single.subscribe(DebugSubscriber(label = label))
        Thread.sleep(2000L)
    }
}