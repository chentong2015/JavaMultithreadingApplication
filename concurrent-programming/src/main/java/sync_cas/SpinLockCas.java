package sync_cas;

import java.util.concurrent.atomic.AtomicReference;

public class SpinLockCas {

    // 被原子更新操作的对象引用
    private AtomicReference<Thread> threadOwnLock = new AtomicReference<>();

    // TODO. 设置Spin期间: yield()线程放弃其当前对处理器的使用，减少对CPU占用(Over-Utilise)
    public void lock() {
        Thread current = Thread.currentThread();
        while (!threadOwnLock.compareAndSet(null, current)) {
            Thread.yield();
            Thread.onSpinWait();
        }
        System.out.println("Get lock and set by thread " + current.getName());
    }

    // 只有持有锁的线程才能释放, 其他线程调用这个unlock方法没有意义
    public void unlock() {
        Thread current = Thread.currentThread();
        if (!threadOwnLock.compareAndSet(current, null)) {
            throw new IllegalMonitorStateException("Not lock owner");
        }
        System.out.println("Release lock for thread " + current.getName());
    }
}
