package subscribers

import io.reactivex.rxjava3.core.MaybeObserver
import io.reactivex.rxjava3.disposables.Disposable

class DebugMaybeObserver<T>(private var label: String = "") : MaybeObserver<T> {
    override fun onSuccess(t: T) {
        val threadName = Thread.currentThread().name
        println("label: $label, threadName: $threadName, data: $t")
    }

    override fun onComplete() {
        val threadName = Thread.currentThread().name
        println("label: $label, threadName: $threadName completed!")
    }

    override fun onSubscribe(d: Disposable?) {
        val threadName = Thread.currentThread().name
        println("label: $label, threadName: $threadName, isDisposed: ${d?.isDisposed}")
    }

    override fun onError(e: Throwable?) {
        val threadName = Thread.currentThread().name
        println("label: $label, threadName: $threadName, throwable: $e")
    }
}