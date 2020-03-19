package com.google.android.play.core.internal

import android.content.Context
import android.util.Log
import java.io.File
import kotlin.random.Random

/**
 * @author: Created By nealkyliu
 * @date: 2020-03-10
 **/
object SoLoadCrashChecker {
    private const val LOG_TAG = "SoLoadCrashChecker"

    private fun testDFInstalling(context: Context) {
        Thread(Runnable {

            warn("testDFInstalling start")

            val random = Random(10)

            for (i in 1..10000000) {

                val set = random.nextInt(1, 100).let {
                    File("/system/auto$it/$it")
                }.let {
                    HashSet<File>().apply { add(it) }
                }


                ag.a().a(context.classLoader, set)

            }
            warn("testDFInstalling End")
        }).start()
    }

    private fun testLoadLibrary(context: Context) {
        Thread(Runnable {
            Thread.sleep(10)

            for (i in 1..100000) {
                info("testLoadLibrary start $i")
                System.loadLibrary("hello1")
                info("testLoadLibrary End $i")
            }
        }).start()
    }

    fun test(context: Context) {
        testDFInstalling(context)
        testLoadLibrary(context)
    }

    private fun info(msg: String) {
        Log.i(LOG_TAG, msg)
    }

    private fun warn(msg: String) {
        Log.w(LOG_TAG, msg)
    }

    private fun error(msg: String, e: Throwable) {
        Log.e(LOG_TAG, msg, e)
    }

}