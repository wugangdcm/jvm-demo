package com.wdcm.jvm.gc;

class A {
    private static B b;//2、方法区中的类静态属性引用的对象、
    private static final C c = new C();//3、方法区中常量引用的对象、
}

class B {

}

class C {

}


/**
 * 哪些可以作为GCRoots对象：
 * 1、虚拟机栈中引用的对象、
 * 2、方法区中的类静态属性引用的对象、
 * 3、方法区中常量引用的对象、
 * 4、本地方法栈中JNI(Native方法)引用的对象
 */
public class GcRootsDemo {
    public static void main(String[] args) {
        A a = new A(); // a 虚拟机栈中引用的对象
    }
}
