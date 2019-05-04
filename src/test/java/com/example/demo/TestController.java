package com.buy.demo;

import com.buy.config.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Scanner;


/**
 * @ClassName TestController
 * @Author yrp
 * @Date 2018/11/1 11:45
 */
@RestController
public class TestController {

    @Autowired
    private RedisService redisService;

    private Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    public static void main(String[] args){

        /*final int number = 1;
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        *//**
         * Java 8 新特性 遍历集合
         *//*
        list.forEach(lists -> {
            System.out.print(lists);
            System.out.println(number);
        });*/

        /*File file = new File("F:\\下载");
        File[] listFile =  file.listFiles();
        for (int i = 0; i < listFile.length; i++) {
            if (listFile[i].isDirectory())
                System.out.println(listFile[i]);
        }
        System.out.println(new File("F:\\下载\\9yin_v845").exists());*/


        /*Map map = new ConcurrentHashMap();
        map.put("111","111");
        map.put("222","222");
        map.put("333","333");
        Set<Map.Entry> entrySet = map.entrySet();
        for (Map.Entry entry:entrySet) {
            System.out.println("Key:"+entry.getKey()+",Value:"+entry.getValue());
        }*/

        /*Thread thread1 = new Thread(new Test(),"thread1");
        Thread thread2 = new Thread(new Test(),"thread2");
        Thread thread3 = new Thread(new Test(),"thread3");
        thread1.start();
        thread2.start();
        thread3.start();*/

        //函数式接口
        //Test1 t = y -> System.out.println("cx"+ y);

        //方法和构造函数引用
        //TestConverT<String,Integer> testConverT = Integer::valueOf;
        //Integer nums = testConverT.convert("30");
        //System.out.println(nums);

        //局部变量限制
        //final int num = 1;
        //TestConverT<Integer, String> stringConverter =
        //        (from) -> String.valueOf(from + num);
        //System.out.println(stringConverter.convert(2));

        //Date Api更新
        //LocalDateTime now = LocalDateTime.now();

        RunnableImple run = new RunnableImple();
        Thread t1 = new Thread(run);
        Thread t2 = new Thread(run);
        Thread t3 = new Thread(run);
        t1.start();
        t2.start();
        t3.start();
    }

    /**
     * 冒泡排序
     */
    public void Sort(){
        Scanner scanner = new Scanner(System.in);
        int[] array = new int[10];
        int temp;
        System.out.println("请输入10个排序的数据：");
        for (int i = 0; i < array.length; i++) {
            array[i] = scanner.nextInt();
        }
        for (int i = 0; i < array.length-1; i++) {
            for (int j = 0; j < array.length-i-1; j++) {
                // TODO 大于是升序，小于是降序
                if(array[j] > array[j+1]){
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
        System.out.println("排序之后的顺序为：");
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]+",");
        }
    }
}
class Test implements Runnable{
    @Override
    public void run() {
        System.out.println("线程启动了");
    }
}
