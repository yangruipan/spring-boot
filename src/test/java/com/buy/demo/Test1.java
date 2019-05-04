package com.buy.demo;

//主要用于编译级错误检查，加上该注解，当你写的接口不符合函数式接口定义的时候，编译器会报错
@FunctionalInterface
public interface Test1 {

    public void test1(String y);

//这里如果继续加一个抽象方法便会报错
//    public void test1();

    //default方法可以任意定义
    default String test2(){
        return "123";
    }

    default String test3(){
        return "123";
    }

    //static方法也可以定义
    static void test4(){
        System.out.println("234");
    }
}
