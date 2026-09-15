package sync_cas;

import java.util.concurrent.atomic.AtomicInteger;

public class SpinLock {

    // TODO. 使用AtomicInteger来确保compareAndSet()操作是原子操作
    private AtomicInteger myNum = new AtomicInteger();

    // 线程安全, 等效于myNum.getAndIncrement();
    public void increase() {
        while (true) {
            int oldValue = myNum.get();
            int newValue = oldValue + 1;
            if (myNum.compareAndSet(oldValue, newValue)) {
               break;
            }
        }
    }

    public AtomicInteger getMyNum() {
        return myNum;
    }
}
