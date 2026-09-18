package process;

// 在代码层面获取PID进程ID
public class JavaProcessId {

    // ProcessHandle: 关于Java进程的处理接口, 获取Process进程相关信息
    public static void main(String[] args) {
        long pid = ProcessHandle.current().pid();
        System.out.println(pid);
    }
}