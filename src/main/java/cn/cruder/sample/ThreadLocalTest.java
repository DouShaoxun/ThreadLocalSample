package cn.cruder.sample;

/**
 * @Author: cruder
 * @Date: 2021/11/27/19:40
 */
public class ThreadLocalTest {
    public static void main(String[] args) {
        for (int i = 1; i < 10; i++) {
            new Thread(() -> {
                String name = Thread.currentThread().getName();
                System.out.println(name + " 1 : " + ThreadLocalUtils.getObject());
                System.out.println(name + " 2 : " + ThreadLocalUtils.getObject());
                System.out.println(name + " 3 : " + ThreadLocalUtils.getObject());
            }, "t_" + i).start();
        }

    }





    public static class ThreadLocalUtils {
        /**
         * 这些变量与它们的普通对应变量的不同之处在于，访问一个（通过其get或set方法）的每个线程都有自己的、独立初始化的变量副本
         */
        private static final ThreadLocal<Object> tl = new ThreadLocal<>();

        public static Object getObject() {
            // 获取当前线程内的资源对象
            Object o = tl.get();
            if (o == null) {
                o = new Object();
                tl.set(o);
            }
            return o;
        }
    }
}
