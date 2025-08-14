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
        Log.d(TAG, "Bitmap created: $bitmap, total=${bitmaps.size}")
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
