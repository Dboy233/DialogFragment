package com.dboy.dialog

import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun test() {
        println("消息测试开始")
        Thread {
            MyLooper.lopper()
        }.start()
        Thread.sleep(100)
        MyLooper.addMessage(Message("One Message"))
        Thread.sleep(100)
        MyLooper.addMessage(Message("Two Message"))
        Thread.sleep(100)
        while (true) {
            continue
        }
    }
}

object MyLooper {
    //处理消息队列的类
    val myQueue = MyMessageQueue()

    ///添加一条消息
    fun addMessage(msg: Message) {
        println("添加消息: ${msg.obj}")
        myQueue.addMessage(msg)
    }

    //开始吧
    fun lopper() {
        while (true) {
            val next = myQueue.next()
            println("处理消息---->${next?.obj}")
            if (next == null) {
                return
            }
        }
    }
}

class Message(var obj: Any? = null, var next: Message? = null)

class MyMessageQueue {

    private var message: Message = Message("线程启动")
    //将新来的消息添加到当前消息的屁股后面
    fun addMessage(msg: Message?) {
        message.next = msg
    }
    //检索下一个Message
    fun next(): Message {
        while (true) {
            if (message.next == null) {
                println("重新检查消息 当前被卡住的消息-${message.obj}")
                Thread.sleep(100)
                continue
            }
            val next = message.next
            message = next!!
            return message
        }
    }
}
