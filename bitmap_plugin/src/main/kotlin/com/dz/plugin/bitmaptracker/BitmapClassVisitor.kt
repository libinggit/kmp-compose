package com.dz.plugin.bitmaptracker

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

class BitmapClassVisitor(api: Int, cv: ClassVisitor) : ClassVisitor(api, cv) {
    override fun visitMethod(
        access: Int, name: String?, descriptor: String?, signature: String?, exceptions: Array<out String>?
    ): MethodVisitor {
        println("[BitmapClassVisitor visitMethod方法调用了")

        val mv = super.visitMethod(access, name, descriptor, signature, exceptions)
        return BitmapMethodVisitor(Opcodes.ASM9, mv)
    }
}
