package cn.enaium.learn.asm.learn4;

import cn.enaium.learn.asm.learn2.Learn2;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.MethodVisitor;

import java.io.FileOutputStream;

import static jdk.internal.org.objectweb.asm.Opcodes.*;
import static jdk.internal.org.objectweb.asm.Opcodes.RETURN;

/**
 * Project: asm
 * -----------------------------------------------------------
 * Copyright © 2020-2021 | Enaium | All rights reserved.
 */
public class Learn4 {
    public static void main(String[] args) {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        classWriter.visit(V1_8, ACC_PUBLIC, "cn/enaium/learn/asm/learn4/Learn4Test", null, "java/lang/Object", null);
        MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        methodVisitor.visitTypeInsn(NEW, "java/lang/String");
        methodVisitor.visitLdcInsn("Enaium");
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/String", "<init>", "(Ljava/lang/String;)V", false);

        methodVisitor.visitTypeInsn(NEW, "cn/enaium/learn/asm/learn4/Bean");
        methodVisitor.visitLdcInsn("Enaium");
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "cn/enaium/learn/asm/learn4/Bean", "<init>", "(Ljava/lang/String;)V", false);

        methodVisitor.visitIntInsn(SIPUSH, 2);
        methodVisitor.visitIntInsn(NEWARRAY, T_BYTE);

        methodVisitor.visitInsn(DUP);
        methodVisitor.visitIntInsn(SIPUSH, 0);
        methodVisitor.visitIntInsn(SIPUSH, 1);
        methodVisitor.visitInsn(AASTORE);

        methodVisitor.visitInsn(DUP);
        methodVisitor.visitIntInsn(SIPUSH, 1);
        methodVisitor.visitIntInsn(SIPUSH, 2);
        methodVisitor.visitInsn(AASTORE);

        methodVisitor.visitEnd();
        classWriter.visitEnd();

        try {
            FileOutputStream out = new FileOutputStream(Learn2.class.getResource("/cn/enaium/learn/asm/learn4/").getPath() + "Learn4Test.class");
            out.write(classWriter.toByteArray());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
