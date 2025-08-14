package com.dz.plugin.bitmaptracker

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

class BitmapMethodVisitor(api: Int, mv: MethodVisitor) : MethodVisitor(api, mv) {

    override fun visitMethodInsn(
        opcode: Int,
        owner: String?,
        name: String?,
        descriptor: String?,
        isInterface: Boolean
    ) {
        when {
            // Bitmap.createBitmap(...)
            owner == "android/graphics/Bitmap" && name == "createBitmap" -> {
                println("[BitmapMethodVisitor createBitmap方法调用了")
                super.visitMethodInsn(opcode, owner, name, descriptor, isInterface)
                mv.visitInsn(Opcodes.DUP) // 复制栈顶Bitmap
                mv.visitMethodInsn(
                    Opcodes.INVOKESTATIC,
                    "com/dz/plugin/bitmaptracker/BitmapTracker",
                    "onCreate",
                    "(Landroid/graphics/Bitmap;)V",
                    false
                )
                return
            }

            // Bitmap.recycle()
            owner == "android/graphics/Bitmap" && name == "recycle" -> {
                println("[BitmapMethodVisitor recycle方法调用了")
                mv.visitInsn(Opcodes.DUP)
                mv.visitMethodInsn(
                    Opcodes.INVOKESTATIC,
                    "com/dz/plugin/bitmaptracker/BitmapTracker",
                    "onRecycle",
                    "(Landroid/graphics/Bitmap;)V",
                    false
                )
                super.visitMethodInsn(opcode, owner, name, descriptor, isInterface)
                return
            }

            // BitmapFactory.decodeResource(...)
            owner == "android/graphics/BitmapFactory" && name?.startsWith("decode") == true -> {
                println("[BitmapMethodVisitor decode方法调用了")
                super.visitMethodInsn(opcode, owner, name, descriptor, isInterface)
                mv.visitInsn(Opcodes.DUP) // 复制栈顶Bitmap对象
                mv.visitMethodInsn(
                    Opcodes.INVOKESTATIC,
                    "com/dz/plugin/bitmaptracker/BitmapTracker",
                    "onCreate",
                    "(Landroid/graphics/Bitmap;)V",
                    false
                )
                return
            }

            else -> super.visitMethodInsn(opcode, owner, name, descriptor, isInterface)
        }
    }
}
