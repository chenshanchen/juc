package lock;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/**
 * 手写的 缓存类
 * 需要三个方法：写入、获取、清楚缓存
 */
class MyCache{

    //所有的缓存都需要 volatile 修饰，保证可见性
    private volatile Map<String,Object> map = new HashMap<>();
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    public void put(String key,Object value){
        reentrantReadWriteLock.writeLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在写入： " + key );
            //模拟网络延迟
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //写入数据
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写入完成");
        }finally {
            reentrantReadWriteLock.writeLock().unlock();
        }

    }

    public void get(String key){

        reentrantReadWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在读取");
            //模拟网络延时
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //读取数据
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取完成：" + result);
        }finally {
            reentrantReadWriteLock.readLock().unlock();
        }
    }

    public void clear(){
        map.clear();
    }


}

/**
 * 多个线程同时操作一个资源类没有任何问题 所以为了满足并发量，读取共享资源应该可以同时进行
 * 但是，如果有一个线程想起写(修改)共享资源，就不应该有其他的线程对资源进行读或写
 *
 * 写操作：【原子性+独占】 即整个过程必须是一个完成的统一整体 中间不允许被分割，被打断
 *
 * 小总结：
 * 【读 读】能共存
 * 【读 写】不能共存
 * 【写 写】不能共存
 *
 */
public class ReentrantReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        //模拟5个线程同时对资源进行写操作
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() -> {
                //Lambda 表达式中的局部变量，需要是 final 修饰的   ？？？
                myCache.put(temp+"" , temp+"");
            },String.valueOf(i)).start();
        }

        //模拟5个线程同时对资源进行读操作
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() -> {
                //Lambda 表达式中的局部变量，需要是 final 修饰的   ？？？
                myCache.get(temp+"");
            },String.valueOf(i)).start();
        }
    }


}
