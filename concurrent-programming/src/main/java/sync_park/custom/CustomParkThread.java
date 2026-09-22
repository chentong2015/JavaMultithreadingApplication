package sync_park.custom;

public class CustomParkThread extends Thread {

    private final CustomParkLock parkLock = new CustomParkLock();

    // 多个线程直接会受到Lock锁的约束
    @Override
    public void run() {
        parkLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " got lock");
            Thread.sleep(10000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } finally {
            parkLock.unlock();
            System.out.println(Thread.currentThread().getName() + " released lock");
        }
    }
}
