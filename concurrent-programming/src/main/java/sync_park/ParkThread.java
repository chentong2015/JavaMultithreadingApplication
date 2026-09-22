package sync_park;

import java.util.concurrent.locks.LockSupport;

// TODO. 在持有锁情况下调用Park可能导致死锁
public class ParkThread {

    private final Object lock = new Object();

    public void testParkLock() {
        synchronized(lock) {
            // 持有锁, 挂起线程但不会释放lock !!
            LockSupport.park();
            // 被unpark之后依然持有lock
        }
    }
}
