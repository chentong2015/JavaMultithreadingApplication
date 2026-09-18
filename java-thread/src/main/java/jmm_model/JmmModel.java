package jmm_model;

// TODO. Java线程的启动和调度(基于JMM模型)
// - Java线程都是直接"映射"到OS操作系统原生线程, 调用native方法
// - Java线程调度方式: 采用抢占式(Preemptive Scheduling), 可能会造成线程饥饿
// - Java线程由OS来分配执行的时间: 具体调度完全JVM和OS决定, 输出顺序不可保证
public class JmmModel {

    public static void main(String[] args) {
        Thread thread = new Thread("my thread");
        thread.start();

        // 调用本地方法的实现
        // JVM通过内核系统开放的API(p_thread)来创建线程
        // private native void start0();
    }
}