package cn.enaium.learn.asm.learn1;

import jdk.internal.org.objectweb.asm.ClassReader;

import java.io.IOException;

/**
 * Project: asm
 * -----------------------------------------------------------
 * Copyright © 2020-2021 | Enaium | All rights reserved.
 */
public class Learn1 {
    public static void main(String[] args) throws IOException {
        MyClassVisitor myClassVisitor = new MyClassVisitor();
        ClassReader classReader = new ClassReader(Learn1Test.class.getName());
        classReader.accept(myClassVisitor, 0);
    }
}
