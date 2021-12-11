package subscribers

import io.reactivex.rxjava3.subscribers.DisposableSubscriber

class DebugSubscriber<T> : DisposableSubscriber<T>() {

    override fun onNext(data: T) {
        val threadName = Thread.currentThread().name
        println("threadName: $threadName, data: $data")
    }

    override fun onError(throwable: Throwable?) {
        val threadName = Thread.currentThread().name
        println("threadName: $threadName, throwable: $throwable")
    }

    override fun onComplete() {
        val threadName = Thread.currentThread().name
        println("threadName: $threadName completed!")
    }
}