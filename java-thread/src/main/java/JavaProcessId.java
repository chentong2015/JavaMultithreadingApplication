
// 一个应用程序启动后就是一个进程
public class JavaProcessId {

    public static void main(String[] args) {
        // ProcessHandle: 关于Java进程的处理接口
        long pid = ProcessHandle.current().pid();
        System.out.println(pid);
    }

    // TODO. OS系统有最大进程数量限制, 单个进程中的线程数量也受限(内存空间有限)
    // java.lang.OutOfMemoryError: unable to create native thread
    // possibly out of memory or process/resource limits reached
    public static void testMaxNumThreads() {
        while (true) {
            new Thread(() -> {
                try {
                    Thread.sleep(1000 * 60 * 60 * 24);
                } catch (Exception ex) {
                }
            }).start();
        }
    }
}