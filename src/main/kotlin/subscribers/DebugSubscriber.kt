package subscribers

import io.reactivex.rxjava3.subscribers.DisposableSubscriber

class DebugSubscriber<T>(private val label: String = "") : DisposableSubscriber<T>() {

    override fun onNext(data: T) {
        val threadName = Thread.currentThread().name
        println("label: $label, threadName: $threadName, data: $data")
    }

    override fun onError(throwable: Throwable?) {
        val threadName = Thread.currentThread().name
        println("label: $label, threadName: $threadName, throwable: $throwable")
    }

    override fun onComplete() {
        val threadName = Thread.currentThread().name
        println("label: $label, threadName: $threadName completed!")
    }
}