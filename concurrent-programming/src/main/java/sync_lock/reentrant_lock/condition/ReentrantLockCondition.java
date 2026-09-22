package sync_lock.reentrant_lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCondition {

    int count;
    int putIndex;
    int takeIndex;
    final Object[] items = new Object[10];

    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    // If a put is attempted on a full buffer, then the thread will block until a space becomes available
    // 1. 添加时，首先需要拿到ReentrantLock
    // 2. 如果数组已满，则.await()处于等待，并释放掉拿到的锁，然后while自旋(阻塞)
    // 3. 当数组被通知notFull.signal()非满时，则在这个条件上等待的一个线程被唤醒，然后执行添加
    public void putItem(String x) throws InterruptedException {
        lock.lock();
        try {
            // await() 会“临时释放锁并挂起线程”，但不会退出try代码块，也不会执行finally
            // - 把当前线程放入Condition等待队列
            // - 自动释放lock
            // - 当前线程阻塞挂起: 没有离开try块, 没有执行 finally, 方法还没返回
            // - 其他线程 signal()后await()会重新竞争锁
            while (count == items.length) {
                notFull.await();
            }

            count++;
            items[putIndex++] = x;
            if (putIndex == items.length) {
                putIndex = 0;
            }
            // If any threads are waiting on this condition then one is selected for waking up
            notEmpty.signal();
        } finally {
            // 进入finally语句块时保证线程是有锁
            lock.unlock();
        }
    }

    // If a take is attempted on an empty buffer, then the thread will block until an item becomes available
    // 1. 取值时，首先需要拿到ReentrantLock
    // 2. 如果数组为空，则.await()处于等待，并释放掉拿到的锁，然后while自旋(阻塞)
    // 3. 当数组被通知notEmpty.signal()非空时，则在这个条件上等待的一个线程被唤醒，然后执行添加
    public String takeItem() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }

            count--;
            String x = (String) items[takeIndex++];
            if (takeIndex == items.length) {
                takeIndex = 0;
            }
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        return count;
    }
}
