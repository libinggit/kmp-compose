package com.dz.plugin.bitmaptracker

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.compile.JavaCompile
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Opcodes
import java.io.File

class BitmapInstrumentationPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        println("[BitmapPlugin] 插件生效")

        project.afterEvaluate {
            // Kotlin 编译任务 hook
            project.tasks.withType(KotlinCompile::class.java).configureEach { task ->
                task.doLast {
                    val outputDir = task.destinationDirectory.get().asFile
                    println("[BitmapPlugin] KotlinCompile 输出目录: $outputDir")
                    instrumentClasses(outputDir)
                }
            }

//            // Java 编译任务 hook
//            project.tasks.withType(JavaCompile::class.java).configureEach { task ->
//                task.doLast {
//                    val outputDir = task.destinationDir
//                    println("[BitmapPlugin] JavaCompile 输出目录: $outputDir")
//                    instrumentClasses(outputDir)
//                }
//            }
        }
    }

    private fun instrumentClasses(dir: File) {
        if (!dir.exists()) return
        dir.walkTopDown()
            .filter { it.isFile && it.extension == "class" }
            .forEach { classFile ->
                try {
                    val cr = ClassReader(classFile.readBytes())
                    val cw = ClassWriter(ClassWriter.COMPUTE_MAXS)
                    val cv = BitmapClassVisitor(Opcodes.ASM9, cw)
                    cr.accept(cv, 0)
                    classFile.writeBytes(cw.toByteArray())
                    println("[BitmapPlugin] 插桩完成: $classFile")
                } catch (e: Exception) {
                    println("[BitmapPlugin] 插桩失败: $classFile, ${e.message}")
                }
            }
    }
}
