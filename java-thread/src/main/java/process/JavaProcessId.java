package process;

// 在代码层面获取PID进程ID
public class JavaProcessId {

    // ProcessHandle: 关于Java进程的处理接口, 获取Process进程相关信息
    public static void main(String[] args) {
        long pid = ProcessHandle.current().pid();
        System.out.println(pid);
    }

    // TODO. 同个进程所创建的线程数量有限制(OS系统线程数和内存大小影响)
    // Exception in thread "main" java.lang.OutOfMemoryError:
    // unable to create native thread: possibly out of memory or process/resource limits reached
    //    at java.base/java.lang.Thread.start0(Native Method)
    //    at java.base/java.lang.Thread.start(Thread.java:802)
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