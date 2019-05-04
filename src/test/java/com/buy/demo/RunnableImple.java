package com.buy.demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程
 */
public class RunnableImple implements Runnable {

    private int number = 100;
    //使用同步代码块
//    private Object obj = new Object();
    /**
     * 多线程的run（）方法
     * 使用同步代码块实现线程安全
     */
//    @Override
//    public void run() {
//        while (true){
//            synchronized (obj){
//                if(number>0){
//                    //执行买票操作
//                    //打印输出卖票信息
//                    System.out.println(Thread.currentThread().getName()+"-->正在卖第"+number+"张票！");
//                    number--;
//                }
//            }
//        }
//    }

    //使用同步方法实现线程安全
//    @Override
//    public void run() {
//        while (true){
//            payTicket();
//        }
//    }

    //使用同步方法实现线程安全
//    public synchronized void payTicket(){
//        if(number>0){
//            //执行买票操作
//            //打印输出卖票信息
//            System.out.println(Thread.currentThread().getName()+"-->正在卖第"+number+"张票！");
//            number--;
//        }
//    }

    Lock locks = new ReentrantLock();
    @Override
    public void run() {
        while (true){
            //在可能会出现线程安全问题的代码前调用lock的lock()方法，添加锁。
            locks.lock();
            try {
                Thread.sleep(10);
                if(number>0){
                    //执行买票操作
                    //打印输出卖票信息
                    System.out.println(Thread.currentThread().getName()+"-->正在卖第"+number+"张票！");
                    number--;
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                //在代码之后调用lock的unlock()方法，释放锁。
                locks.unlock();
            }
        }
    }
}
