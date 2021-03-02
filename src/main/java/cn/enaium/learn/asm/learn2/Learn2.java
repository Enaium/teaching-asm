package cn.enaium.learn.asm.learn2;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.MethodVisitor;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

/**
 * Project: asm
 * -----------------------------------------------------------
 * Copyright © 2020-2021 | Enaium | All rights reserved.
 */
public class Learn2 {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        classWriter.visit(V1_8, ACC_PUBLIC, "cn/enaium/learn/asm/learn2/Learn2Test", null, "java/lang/Object", null);
        MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC + ACC_STATIC, "render", "()V", null, null);
        methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        methodVisitor.visitLdcInsn("Hello ASM!");
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        methodVisitor.visitMaxs(2, 1);
        methodVisitor.visitInsn(RETURN);
        methodVisitor.visitEnd();
        classWriter.visitEnd();

        try {
            FileOutputStream out = new FileOutputStream(Learn2.class.getResource("/cn/enaium/learn/asm/learn2/").getPath() + "Learn2Test.class");
            out.write(classWriter.toByteArray());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
