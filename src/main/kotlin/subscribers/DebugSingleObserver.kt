package subscribers

import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable

class DebugSingleObserver<T>(private var label: String = "") : SingleObserver<T> {

    override fun onSuccess(t: T) {
        val threadName = Thread.currentThread().name
        println("label: $label, threadName: $threadName, data: $t")
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