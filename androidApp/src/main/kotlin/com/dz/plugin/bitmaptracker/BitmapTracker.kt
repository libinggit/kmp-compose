package com.dz.plugin.bitmaptracker
import android.graphics.Bitmap
import android.util.Log
import java.lang.ref.WeakReference
import java.util.Collections

object BitmapTracker {
    private val TAG = "BitmapTracker"
    private val bitmaps = Collections.synchronizedSet(mutableSetOf<WeakReference<Bitmap>>())

    @JvmStatic
    fun onCreate(bitmap: Bitmap) {
        bitmaps.add(WeakReference(bitmap))

        // 获取并打印调用栈
        val stackTrace = Thread.currentThread().stackTrace
        val callStackBuilder = StringBuilder()
        // 跳过前面几帧，它们通常是 Thread.getStackTrace 和我们自己的 onCreate 方法。
        // 对于直接在 onCreate 内部调用，通常跳过2帧是合适的：
        // 0: dalvik.system.VMStack.getThreadStackTrace(Native Method) 或 java.lang.Thread.getStackTrace()
        // 1: java.lang.Thread.getStackTrace(Thread.java:...)
        // 2: com.dz.plugin.bitmaptracker.BitmapTracker.onCreate(BitmapTracker.kt:...) <--- 这是我们当前的方法
        // 所以我们想看的是从第3帧开始的调用者
        val skipFrames = 3 // 调整这个数字以获得最相关的栈信息
        callStackBuilder.append("Call Stack for Bitmap: ").append(bitmap.toString())
        if (stackTrace.size > skipFrames) {
            for (i in skipFrames until stackTrace.size) {
                callStackBuilder.append("\n  at ").append(stackTrace[i].toString())
            }
        } else {
            callStackBuilder.append("\n  (Stack trace too short or unavailable)")
        }

        Log.d(TAG, "Bitmap created: $bitmap, total=${bitmaps.size}\n$callStackBuilder")
        cleanup()
    }

    @JvmStatic
    fun onRecycle(bitmap: Bitmap) {
        bitmaps.removeAll { it.get() === bitmap || it.get() == null }
        Log.d(TAG, "Bitmap recycled, total=${bitmaps.size}")
    }

    fun recycleAll(){
        bitmaps.forEach {
            it.get()?.recycle()
        }
        bitmaps.clear()
    }

    private fun cleanup() {
        // 移除已经被回收的 Bitmap
        bitmaps.removeAll { it.get() == null }
    }

    fun dump() {
        cleanup()
        Log.d(TAG,"Current alive bitmaps: ${bitmaps.size}")
    }
}
