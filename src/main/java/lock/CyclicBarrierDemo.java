package lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 的字面意思是可循环(Cyclic)使用的屏障(barrier)
 * 它要做的事情是，让一组线程到达一个屏障(也可以叫做同步点)时被阻塞，直到最后一个线程达到屏障时，屏障才会开门
 * 所有被拦截的线程才会继续干活，线程进入屏障通过 CyclicBarrier 的 await()
 *
 * 【相当于做加法】
 *
 * 简单来说：
 * 就是只有集齐7颗龙珠才能召唤神龙
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        /*
         * public CyclicBarrier(int parties, Runnable barrierAction)
         * parties:需要的触发 Runnable barrierAction 线程执行的条件的个数：【集齐7颗龙珠】
         * Runnable barrierAction：集齐触发条件之后运行的线程 【召唤神龙】
         */
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,() -> {
            System.out.println(Thread.currentThread().getName() + "\t 召唤神龙");
        });

        //集齐7颗龙珠
        for (int i = 1; i <= 7 ; i++) {
            final int temp = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 集齐第"+ temp+ "颗龙珠");
                //每次线程执行完之后，没有到触发个数，需要阻塞
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }

    }
}
