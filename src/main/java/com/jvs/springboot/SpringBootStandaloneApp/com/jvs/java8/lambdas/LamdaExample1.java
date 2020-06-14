package com.jvs.springboot.SpringBootStandaloneApp.com.jvs.java8.lambdas;

public class LamdaExample1 {

    public static void main(String[] args) {
        new MyClass(() -> System.out.println("MyFuncInterface runs")).start();

        new MyClass().start();

        MyFuncInterface myFuncInterface = () -> System.out.println("xxxx");
        myFuncInterface.run();

        UpperConcat uc = (s1, s2) -> s1.toUpperCase() + s2.toUpperCase();
        String myString = doStringStuff(uc, "Hola", "Miguel!");
        System.out.println(myString);

    }

    public static String doStringStuff(UpperConcat uc, String s1, String s2){
        return uc.upperAndConcat(s1, s2);
    }
}

interface UpperConcat {
    public String upperAndConcat(String s1, String s2);
}


@FunctionalInterface
interface MyFuncInterface {
    void run();
}

class MyClass implements MyFuncInterface {
    MyFuncInterface target;

    MyClass() {
        this.target = this;
    }

    MyClass(MyFuncInterface myFuncInterface){
        this.target = myFuncInterface;
    }

    void start() {
        target.run();
    }

    @Override
    public void run() {
        System.out.println("MyClass runs");
    }
}