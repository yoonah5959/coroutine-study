import kotlinx.coroutines.*
import java.lang.UnsupportedOperationException

fun main() {
//    runBlockingExamOfGlobalScope()
//    runBlockingExam()
//    runBlockingExamOfGlobalScope2()
    joinExam()
}


fun runBlockingExamOfGlobalScope() = runBlocking {
    val deferred = GlobalScope.async { doException() } //이러면 안터지고
    deferred.join()
    if (deferred.isCancelled) {
        println("isCancelled")
    }
    println("complete")
}

@InternalCoroutinesApi
fun runBlockingExamOfGlobalScope2() = runBlocking {
    val deferred = GlobalScope.async { doException() }
    deferred.getCancellationException()
    deferred.await() // 이러면 터짐
    if (deferred.isCancelled) {
        println("isCancelled")
    }
    println("complete")
}

fun runBlockingExam() = runBlocking {
    val deferred2 = async { doException() } //이러면 터짐
    deferred2.join()
}

fun joinExam() = runBlocking {
    println("1")
    val job1 = launch {
        println("join1 start")
        delay(300)
        println("join1 end")
    }
    job1.join()
    println("2")

    val job2 = launch {
        println("join2 start")
        delay(300)
        println("join2 end")
    }
    println("3")
    println("4")
    val list = ArrayList<String>()
    list.map {
        "`"
    }.toMutableList()

    val list2 = MutableList()
}

fun doException() {
    throw UnsupportedOperationException("harry error")
}

class chapter2 {

}