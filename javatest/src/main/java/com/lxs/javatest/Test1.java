package com.lxs.javatest;

/**
 * 实现功能：
 * Created by lvxinsheng on 2019/1/24 下午1:02
 */
public class Test1 {
    private int i;
    class In {
        private String str = "";
    }
    void test() {
        System.out.println(new In().str);
    }

    public static void main(String[] args) {
//        P c = new C();
//        String s = c.tag;
//        String s = ((P)c).tag;
        Test1 t = new Test1();
        t.test();

        P c = new C("");
        if (c instanceof C) {

        }
        int tag = ((C) c).tag;
        int d = c.b;
        Object o = "1";
        if (o instanceof Math) {

        }
    }
}
interface in {
      class inin {

    }

}

class P {
    private interface inter {

    }
    public static final int c;
    static {
        c = 0;
    }
    static int b;
    int a;

    public P(int a) {

    }

    public String tag = "1";

    P() {
    }
}

class C extends P {
    static int b;
    int a;
    public int tag = 1;

    //    public C(int a) {
//        super(a);
//    }
    public C(String a) {
    }
}
