package process;

// 在代码层面获取线程ID
// 线程ID从1开始递增，仅在JVM内部有意义，不等于操作系统线程ID
public class JavaThreadId {

    public static void main(String[] args) throws InterruptedException {
        long pid = ProcessHandle.current().pid();
        System.out.println(pid);

        long tid = Thread.currentThread().getId(); // main thread
        System.out.println("Thread ID: " + tid);

        new Thread(() -> {
            long newTid = Thread.currentThread().getId(); // Thread-0 id
            System.out.println("Run new thread ID: " + newTid);
        }).start();

        // 获取所有线程的ID
        Thread.getAllStackTraces().keySet().forEach(t -> {
            System.out.println(t.getName() + " : " + t.getId());
        });
    }
}