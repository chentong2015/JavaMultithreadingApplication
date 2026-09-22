package collections.queue_delay;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

// DelayedTask 表示在特定时刻(延迟的时间点)执行的任务
public class MyDelayedTask implements Delayed {

    private String name;
    private long startTime; // milliseconds

    public MyDelayedTask(String name, long delay) {
        this.name = name;
        this.startTime = System.currentTimeMillis() + delay;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = startTime - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        MyDelayedTask task = (MyDelayedTask) o;
        return (int) (this.startTime - task.startTime);
    }

    public void run() {
        System.out.println("Run task: " + name);
        try {
            Thread.sleep(20000);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
