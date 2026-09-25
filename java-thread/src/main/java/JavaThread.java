
public class JavaThread {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread("my thread");
        thread.start();
        // TODO. 调用native本地方法的实现
        // JVM通过内核系统开放的API(p_thread)来创建线程
        // private native void start0();

        // TID: Thread ID 1开始递增, 仅在JVM内部有意义
        // NID: Native ID 操作系统层面的线程ID(注意Ox十六进制转换十进制, 对应OS系统线程ID)
        long tid = Thread.currentThread().threadId(); // main thread
        System.out.println("Thread ID: " + tid);

        new Thread(() -> {
            long newTid = Thread.currentThread().threadId(); // Thread-0 id
            System.out.println("Run new thread ID: " + newTid);
        }).start();

        // 获取所有线程的ID
        // Thread.getAllStackTraces().keySet().forEach(t -> {
        //     System.out.println(t.getName() + " : " + t.getId());
        // });
    }
}