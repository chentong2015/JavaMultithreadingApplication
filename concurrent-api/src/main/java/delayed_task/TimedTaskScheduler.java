package delayed_task;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class TimedTaskScheduler {

    public static void main(String[] args) {
        DelayQueue<DelayedTask> queue = new DelayQueue<>();
        new Thread(new TaskProducer(queue), "Producer Thread").start();
        new Thread(new TaskConsumer(queue), "Consumer Thread").start();
    }

    public void testDelayQueue() throws InterruptedException {
        DelayQueue<MyDelayTask> queue = new DelayQueue<>();
        queue.add(new MyDelayTask());
        queue.put(new MyDelayTask());
        queue.offer(new MyDelayTask());

        MyDelayTask task = queue.take();
    }

    class MyDelayTask implements Delayed {
        @Override
        public long getDelay(TimeUnit unit) {
            return 0;
        }

        @Override
        public int compareTo(Delayed o) {
            return 0;
        }
    }
}
