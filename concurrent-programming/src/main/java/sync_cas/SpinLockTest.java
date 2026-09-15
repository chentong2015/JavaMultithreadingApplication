package sync_cas;

public class SpinLockTest {

    private static int count = 0;
    private static SpinLockCas casLockImpl = new SpinLockCas();

    private static void increaseCount() {
        casLockImpl.lock();
        try {
            for (int index = 0; index < 20000; index++) {
                count++;
            }
        } finally {
            casLockImpl.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 测试普通自旋锁的原子操作
        SpinLock casLock = new SpinLock();
        for (int index = 0; index < 1000; index++) {
            new Thread(casLock::increase).start();
        }
        Thread.sleep(2000);
        System.out.println(casLock.getMyNum());

        // 使用自定义的自旋锁来累加数据
        for (int index = 0; index < 3; index++) {
            new Thread(SpinLockTest::increaseCount, "thread " + index).start();
        }
        Thread.sleep(2000);
        System.out.println(count);
    }
}
