package com.buy.demo1;

/**
 * 包子类
 */
public class BaoZi {
    //包子皮
    private String pi;
    //包子馅
    private String xian;
    //包子的状态
    private boolean status = false;

    public String getPi() {
        return pi;
    }

    public void setPi(String pi) {
        this.pi = pi;
    }

    public String getXian() {
        return xian;
    }

    public void setXian(String xian) {
        this.xian = xian;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
