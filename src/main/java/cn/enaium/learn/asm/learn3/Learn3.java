package cn.enaium.learn.asm.learn3;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Project: asm
 * -----------------------------------------------------------
 * Copyright © 2020-2021 | Enaium | All rights reserved.
 */
public class Learn3 {
    public static void main(String[] args) throws IOException {
        ClassReader classReader = new ClassReader(Learn3Test.class.getName());
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        MyClassVisitor myClassVisitor = new MyClassVisitor(classWriter);
        classReader.accept(myClassVisitor, 0);
        FileOutputStream fileOutputStream = new FileOutputStream(Learn3.class.getResource("/cn/enaium/learn/asm/learn3/Learn3Test.class").getPath());
        fileOutputStream.write(classWriter.toByteArray());
        fileOutputStream.flush();
    }
}
