package com.buy.demo1;

/**
 * 测试类
 */
public class Test {
    public static void main(String[] args){
        BaoZi baoZi = new BaoZi();
        BaoZiPu baoZiPu = new BaoZiPu(baoZi);
        ChiHuo chiHuo = new ChiHuo(baoZi);
        Thread t0 = new Thread(baoZiPu);
        Thread t1 = new Thread(chiHuo);
        t0.start();
        t1.start();
    }
}
