package lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 手写一个 自旋锁
 */
public class SpinLock {

    //原子引用线程
    AtomicReference<Thread> atomicReference = new AtomicReference<Thread>();

    public void myLock(){
        //获取当前线程
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t coming in");
        while(!atomicReference.compareAndSet(null,thread)){

        }

    }

    public void myUnLock(){
        //获取当前线程
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName() + "\t invoke unlock");
    }


    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();

        new Thread(() -> {
            spinLock.myLock();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLock.myUnLock();
        },"t1").start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            spinLock.myLock();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLock.myUnLock();
        },"t2").start();
    }



}
