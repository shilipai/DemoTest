package com.lxs.javatest;


/**
 * 实现功能：
 * Created by lvxinsheng on 2018/12/12 下午4:08
 */
public class Aaa<T> {
    private T info;
    public Aaa() {}
    public Aaa(T info) {
        this.info = info;
    }

    public void setInfo(T info) {
        this.info = info;
    }
    public T getInfo() {
        return info;
    }

    public static void main(String[] args) {
        Aaa a = new Aaaa();
        ((Aaaa) a).test();
    }

}

class Aaaa extends Aaa {

    public void test() {}

    @Override
    public String getInfo() {
        return super.getInfo().toString();
    }
}
