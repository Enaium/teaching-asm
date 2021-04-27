package cn.enaium.learn.asm.learn6;

import cn.enaium.learn.asm.learn2.Learn2;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.tree.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ListIterator;

import static jdk.internal.org.objectweb.asm.Opcodes.*;
import static jdk.internal.org.objectweb.asm.Opcodes.RETURN;

/**
 * @author Enaium
 */
public class Learn6 {
    public static void main(String[] args) {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        ClassNode classNode = new ClassNode();
        classNode.visit(V1_8, ACC_PUBLIC, "cn/enaium/learn/asm/learn6/Learn6Test", null, "java/lang/Object", null);
        MethodNode methodNode = new MethodNode(ACC_PUBLIC + ACC_STATIC, "render", "()V", null, null);
        methodNode.instructions.add(new FieldInsnNode(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"));
        methodNode.instructions.add(new LdcInsnNode("Hello ASM!"));
        methodNode.instructions.add(new MethodInsnNode(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false));
        methodNode.visitMaxs(2, 1);
        methodNode.instructions.add(new InsnNode(RETURN));
        classNode.methods.add(methodNode);
        classNode.accept(classWriter);

        String name = Learn2.class.getResource("/cn/enaium/learn/asm/learn6/").getPath() + "Learn6Test.class";

        try {
            FileOutputStream out = new FileOutputStream(name);
            out.write(classWriter.toByteArray());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            ClassReader classReader = new ClassReader(new FileInputStream(name));
            ClassNode readClassNode = new ClassNode();
            classReader.accept(readClassNode,0);
            System.out.println(readClassNode.name);
            for (MethodNode method : readClassNode.methods) {
                System.out.println(method.name);
                ListIterator<AbstractInsnNode> iterator = method.instructions.iterator();
                while (iterator.hasNext()) {
                    AbstractInsnNode next = iterator.next();
                    System.out.println(next.getClass());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
