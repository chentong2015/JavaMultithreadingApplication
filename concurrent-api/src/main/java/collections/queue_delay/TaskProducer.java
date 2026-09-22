package collections.queue_delay;

import java.util.Random;
import java.util.concurrent.DelayQueue;

// 生产者直接在延迟队列中添加消费者要执行的延迟任务Task
public class TaskProducer implements Runnable {

    private final DelayQueue<MyDelayedTask> queue;
    private final Random random;

    public TaskProducer(DelayQueue<MyDelayedTask> queue) {
        this.queue = queue;
        this.random = new Random();
    }

    @Override
    public void run() {
        queue.put(new MyDelayedTask("task name 1", random.nextInt(10000)));
        queue.put(new MyDelayedTask("task name 2", random.nextInt(10000)));
        queue.put(new MyDelayedTask("task name 3", random.nextInt(10000)));
        queue.put(new MyDelayedTask("task name 4", random.nextInt(10000)));
    }
}
