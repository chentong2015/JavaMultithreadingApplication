package sync_park.custom;

public class TestParkLock {

    // 模拟线程获取锁和释放做的过程
    // Thread-0 got lock
    // Thread-0 released lock
    // Thread-3 got lock
    // Thread-3 released lock
    // Thread-2 got lock
    // Thread-2 released lock
    // Thread-1 got lock
    // Thread-1 released lock
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new CustomParkThread().start();
        }
    }
}
