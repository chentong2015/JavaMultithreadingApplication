package collections.queue_delay;

import java.util.concurrent.DelayQueue;

// TODO. 消费者始终在准确时间获取要执行的任务
// 消费者不需要关心时间差，直接在while循环里获取“延迟执行”的任务
// 当延迟队列中没有任务时，消费者会无限等待(阻塞)直到被唤醒，不会消耗CPU
//
// Get task: DelayedTask{name='task name 3', startTime=1735989602389} 起始时间最早
// Get task: DelayedTask{name='task name 1', startTime=1735989603447}
// Get task: DelayedTask{name='task name 4', startTime=1735989607305}
// Get task: DelayedTask{name='task name 2', startTime=1735989608651}
public class TestConsumerProducer {

    public static void main(String[] args) {
        DelayQueue<MyDelayedTask> queue = new DelayQueue<>();
        new Thread(new TaskProducer(queue), "Producer Thread").start();
        new Thread(new TaskConsumer(queue), "Consumer Thread").start();
    }
}
