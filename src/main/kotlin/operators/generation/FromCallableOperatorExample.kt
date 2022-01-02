package operators.generation

import io.reactivex.rxjava3.core.Flowable
import subscribers.DebugSubscriber

class FromCallableOperatorExample {

    /***
     * [Result]
     * current timestamp [ms]
     * completed!
     */
    fun executeFromCallable() {
        val flowable: Flowable<Long> = Flowable.fromCallable { System.currentTimeMillis() }
        flowable.subscribe(DebugSubscriber(label = "from_callable"))
    }
}