package thread_local;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// TODO. ThreadLocal
// 线程独享的本地存储数据(缓存)，限制数据被并发线程访问/修改
// - 管理每个线程各自的DB连接
// - 控制每个线程Transaction/commit数据提交
public class JavaThreadLocal {

    private final ThreadLocal<Map<String, List<Long>>> techIdCache =
            ThreadLocal.withInitial(ConcurrentHashMap::new);

    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("value");

        ThreadLocal<Integer> threadLocal1 = new ThreadLocal<>();
        threadLocal1.set(100);

        // TODO. 主线程中的线程变量不能被另一个线程共享
        new Thread(() -> {
            // Can't get value from ThreadLocal of Main
            System.out.println("new thread, get string :" + threadLocal.get());
            System.out.println("new thread, get int :" + threadLocal1.get());
        }).start();

        // TODO. 只有当前主线程能访问数据
        System.out.println(threadLocal.get());
        System.out.println(threadLocal1.get());

        // Removes the current thread's value for this thread-local variable
        threadLocal.remove();
        System.out.println(threadLocal.get());
    }
}
