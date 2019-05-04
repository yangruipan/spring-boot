package com.buy.demo1;

/**
 * 吃货类线程
 */
public class ChiHuo implements Runnable {

    private BaoZi baoZi;

    public ChiHuo(BaoZi baoZi) {
        this.baoZi = baoZi;
    }

    @Override
    public void run() {
        //让吃货一直吃包子
        while (true){
            //为了保证线程安全，需要添加同步代码块，锁对象（包子）必须保持一直
            synchronized (baoZi){
                if(baoZi.isStatus() == false){
                    try {
                        baoZi.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("吃货正在吃："+baoZi.getPi()+baoZi.getXian()+"的包子！");
                //吃完之后需要把包子的状态改为false；
                baoZi.setStatus(false);
                //包子吃完之后需要唤醒包子铺线程去生产包子；
                baoZi.notify();
                System.out.println("吃货已经把："+baoZi.getPi()+baoZi.getXian()+"的包子吃完了，包子铺开始生产包子！");
                System.out.println("-----------------------------------------------------------------------------");
            }
        }
    }
}
