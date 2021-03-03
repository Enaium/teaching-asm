package cn.enaium.learn.asm.learn5;

import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.MethodVisitor;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

/**
 * Project: asm
 * -----------------------------------------------------------
 * Copyright © 2020-2021 | Enaium | All rights reserved.
 */
public class MyClassVisitor extends ClassVisitor {
    public MyClassVisitor(ClassWriter classWriter) {
        super(ASM5, classWriter);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        return new MethodVisitor(api, super.visitMethod(access, name, desc, signature, exceptions)) {
            @Override
            public void visitLdcInsn(Object cst) {
                if (cst instanceof String) {
                    byte[] bytes = ((String) cst).getBytes();
                    mv.visitTypeInsn(NEW, "java/lang/String");
                    mv.visitInsn(DUP);
                    mv.visitIntInsn(SIPUSH, bytes.length);
                    mv.visitIntInsn(NEWARRAY, T_BYTE);
                    for (int i = 0; i < bytes.length; i++) {
                        mv.visitInsn(DUP);
                        mv.visitIntInsn(SIPUSH, i);
                        mv.visitIntInsn(SIPUSH, bytes[i]);
                        mv.visitInsn(AASTORE);
                    }
                    mv.visitLdcInsn("UTF-8");
                    mv.visitMethodInsn(INVOKESPECIAL, "java/lang/String", "<init>", "([BLjava/lang/String;)V", false);
                } else {
                    super.visitLdcInsn(cst);
                }
            }
        };
    }
}
