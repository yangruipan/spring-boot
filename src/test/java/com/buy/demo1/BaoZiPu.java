package com.buy.demo1;

/**
 * 包子铺类线程
 */
public class BaoZiPu implements Runnable {

    //创建包子成员变量
    private BaoZi baoZi;
    //创建带参数的包子铺构造函数
    public BaoZiPu(BaoZi baoZi) {
        this.baoZi = baoZi;
    }

    @Override
    public void run() {
        int count = 0;
        //让包子铺一直生产包子
        while (true){
            synchronized (baoZi){
                if(baoZi.isStatus() == true){
                    try {
                        baoZi.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                //如果包子铺中没有包子，需要生产包子
                if(count%2 == 0){
                    //生产薄皮 三鲜馅包子
                    baoZi.setPi("薄皮");
                    baoZi.setXian("三鲜馅");
                }else{
                    //生产冰皮 猪肉大葱包子
                    baoZi.setPi("冰皮");
                    baoZi.setXian("猪肉大葱");
                }
                count++;
                System.out.println("包子铺正在生产："+baoZi.getPi()+baoZi.getXian()+"的包子！");
                //生产包子等待3秒钟
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //生产好了之后需要修改包子的状态
                baoZi.setStatus(true);
                //生产好了包子之后需要去唤醒吃货线程，执行吃包子动作
                baoZi.notify();
                System.out.println("包子铺已经生产好了："+baoZi.getPi()+baoZi.getXian()+"的包子，吃货可以开始吃了！");
            }
        }
    }
}
