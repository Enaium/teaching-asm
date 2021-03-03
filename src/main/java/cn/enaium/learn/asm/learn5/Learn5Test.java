package cn.enaium.learn.asm.learn5;

/**
 * Project: asm
 * -----------------------------------------------------------
 * Copyright © 2020-2021 | Enaium | All rights reserved.
 */
public class Learn5Test {
    private final String name = "Enaium";

    private Learn5Test() {
        render(name);
    }

    private void render() {
        System.out.println("obfuscatory by " + name);
    }

    private void render(String text) {
        System.out.println(text);
    }
}
