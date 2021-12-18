package operators

import io.reactivex.rxjava3.core.Flowable
import subscribers.DebugSubscriber

class ErrorOperatorExample {

    /**
     * [Result]
     * threadName: main, throwable: java.lang.Throwable: error!: For input string: "hoge"
     */
    fun executeError() {
        val label = "error"
        val flowableJust = Flowable.just("hoge").flatMap {
            try {
                val message = it.toInt() // fail to cast.
                Flowable.just(message)
            } catch (e: Exception) {
                val flowableError: Flowable<Error> = Flowable.error(Throwable("error!: ${e.message}"))
                flowableError
            }
        }

        flowableJust.subscribe(DebugSubscriber(label = label))
    }
}