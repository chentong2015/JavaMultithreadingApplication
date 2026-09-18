package thread_lifecycle;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class JavaThreadFactory {

    // 使用线程工厂来创建特定实现的线程
    static class MyThreadFactory implements ThreadFactory {

        @Override
        public Thread newThread(Runnable r) {
            Thread t = Executors.defaultThreadFactory().newThread(r);
            t.setName("Thread name");
            t.setDaemon(true);
            return t;
        }
    }

    public static void main(String[] args) {
        Thread myThread = new MyThreadFactory()
                .newThread(() -> System.out.println("run thread"));
        myThread.start();
    }
}
