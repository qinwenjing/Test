package exceptiontest;

import java.io.IOException;

/**
 * 对于检查异常，必须处理，否则代码标红都编译不了。处理方式：继续抛出，用try..catch捕获。
 * 对于非检查异常，可以编译通过，这是因为编译器认为可以人为避免，但是运行时程序中抛出错误的话，代码运行时就报错了，处理方式包括：捕获、继续抛出、不处理
 *
 * 检查异常（checked exception）特点：
 * 非检查异常（unchecked exception）特点：
 *
 * 哪些是检查异常（checked exception）：除了RuntimeException，都是非检查
 * 哪些是非检查异常（unchecked exception）：实现RuntimeException的都是非检查，所以非检查异常也叫运行时异常
 */
public class Test1 {
    // ArithmeticException extends RuntimeException extends Exception ,继承的是运行时异常，它属于非检查异常
    public static void doSomthing() throws ArithmeticException{
        System.out.println("doSomething");
    }

    // IOException extends Exception extends Throwable， 他属于检查异常
    public static void doSomthingIo() throws IOException {
        System.out.println("doSomething");
    }

    public static void main(String[] args) {
        // 可以编译通过
        // doSomthing();
        // 直接标红，编译不通过
        // doSomthingIo();
    }
}
