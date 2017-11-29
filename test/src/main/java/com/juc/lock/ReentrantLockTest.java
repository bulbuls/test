package com.juc.lock;

import java.util.LinkedHashSet;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/**
 * Lock 与 Condition
 * Lock:代表着线程间的互斥操作 它对应的是synchronized关键字
 * Condition：代表着线程间的通信起对应的是Object中的操作
 * 			condition中维护着一个single队列，AQS中维护者等待队列，
 * 			await：将线程包装成一个node放到single中，并移出AQS；然后循环判断node是否重新回到AQS中
 * 			single：将node从single队列一到AQS中去
 * AQS：lock/tryAcquire（尝试获取锁） 这俩个步骤交给了子类实现。AQS只告诉子类当前的state状态值并保证状态值的可见性，至于是什么锁交给子类去实现
 * FairSync：公平锁，根据入队的顺序依次获得锁，1：先尝试获得锁，2，没有得到则将线程放到等待队列中去，然后循环尝试获得锁
 * NonFairSync：非公平锁
 * @author TF017477
 *
 */
public class ReentrantLockTest {
	
	public static void main(String[] args) {
		/*ReentrantLock lock = new ReentrantLock();

		MyThread a = new MyThread(lock);
		MyThread b = new MyThread(lock);
		
		new Thread(a).start();
		new Thread(b).start();*/
		LinkedHashSet<String> mLinkedSetString = new LinkedHashSet<String>(); 
		mLinkedSetString.add("A");
		mLinkedSetString.add("B");
		mLinkedSetString.add("C");
		mLinkedSetString.add("D");
	}
}
class MyThread implements Runnable{
	private ReentrantLock lock;
	public MyThread(ReentrantLock lock){
		this.lock = lock;
	}
	@Override
	public void run() {
		Condition a = lock.newCondition();
		lock.lock();
		try {
			Thread.sleep(30000000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lock.unlock();
		System.out.println(Thread.currentThread().getName());
	}
}
