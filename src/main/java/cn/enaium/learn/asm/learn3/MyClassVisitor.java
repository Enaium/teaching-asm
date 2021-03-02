package cn.enaium.learn.asm.learn3;

import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.FieldVisitor;
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
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        if (name.equals("aBoolean")) {
            return null;
        }
        return super.visitField(access, name, desc, signature, value);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        if (name.equals("render")) {
            return null;
        }
        return super.visitMethod(access, name, desc, signature, exceptions);
    }

    @Override
    public void visitEnd() {
        super.visitField(ACC_PRIVATE, "name", "Ljava/lang/String;", null, null).visitEnd();
        super.visitMethod(ACC_PRIVATE, "name", "(Ljava/lang/String;)V", null, null).visitEnd();
        super.visitEnd();
    }
}
