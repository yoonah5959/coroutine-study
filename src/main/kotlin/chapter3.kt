import kotlinx.coroutines.*

//fun main() {
////    runBlocking {
//////    runBlockingAndLaunch()
//////    test()
//////    testOfGlobalScope()
//////    testOJoin()
//////    testOfCoroutineScope()
//////    coroutineScopeHarry<String>()
////       coroutineStartTest()
////    }
//}

fun runBlockingAndLaunch() = runBlocking {
    launch {

    }
}

//launch는 사실 CoroutineScope.launch 임
//launch리턴은 Job임


//100page
//complete 찍힌 후 에러남
fun test() = runBlocking {
    launch {
        TODO("error")
    }
    delay(100)
    println("complete")
}

//GlobalScope라 그런가 에러가 안남, complete찍고 끝남
fun testOfGlobalScope() = runBlocking {
    GlobalScope.launch {
        TODO("error")
    }
    println("complete")
}

//join
//fun testOJoin() = runBlocking {
//    val job = launch {
//        TODO("error")
//    }
//    job.join()
//    println("complete")
//}


suspend fun testOfCoroutineScope() = coroutineScope {
    println("runBlocking ${Thread.currentThread()}")
    launch {
        println("1 ${Thread.currentThread()}")
        launch {
            println("2 ${Thread.currentThread()}")
        }
        println("3 ${Thread.currentThread()}")
    }
    println("runBlocking2 ${Thread.currentThread()}")
}

fun <R> coroutineScopeHarry(block: suspend CoroutineScope.() -> R) {
}


suspend fun coroutineStartTest() = coroutineScope {
    val j = launch(start = CoroutineStart.LAZY) {
        println("coroutineStartTest")
    }

    j.start()
}


/*
GlobalScope.launch 안돌아가는 이유는 코루틴 실행이 될려고할때 메인이 끝나서 그럼
 */
//fun main() {
//    val job = GlobalScope.launch {
//        println("1")
//        delay(1000)
//        println("2")
//    }
//   // Thread.sleep(10L)  메인 안죽게 하면됨
//}



/*
start vs join
 */
//fun main() {
//    examStart()
//    examJoin()
//}
//
//fun examStart() = runBlocking{
//    val job = GlobalScope.launch(start = CoroutineStart.LAZY) {
//        println("examStart1")
//        delay(1000)
//        println("examStart2")
//    }
//    job.start()
//}
//
//fun examJoin(): Unit = runBlocking{
//    val job = GlobalScope.launch(start = CoroutineStart.LAZY) {
//        println("examJoin1")
//        delay(100)
//        println("examJoin2")
//    }
//    job.join()
//}




//cancelAndJoin exam
fun examOfCancelAndJoin(){
  val j =  GlobalScope.launch {
        delay(100)
    }
//    j.cancelAndJoin() // cancelAndJoin이 suspend함수라서 코루틴 안이나 다른 suspend 함수 안에서 돌아가야함
}


@InternalCoroutinesApi
fun main() {
    examOfCancel()
}
//cancel 예제
@InternalCoroutinesApi
fun examOfCancel() = runBlocking{
    val j =  GlobalScope.launch {
        delay(5000)
    }
    delay(2000)
    val cancelExeption = j.getCancellationException()
    println(cancelExeption.message)
}