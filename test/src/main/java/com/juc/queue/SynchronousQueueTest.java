package com.juc.queue;

import java.util.concurrent.SynchronousQueue;
/**
 * SynchronousQueueTest队列
 * 特点：可以看作一个大小为1的阻塞队列，实际上他并不存储任何节点，仅仅作为一个传球手传递数据。
 * 	   一来一去不能多也不能少。
 * 
 * @author TF017477
 *
 */
public class SynchronousQueueTest {

	private final static SynchronousQueue<String> queue = new SynchronousQueue<String>(true);

	public static void main(String[] args) throws InterruptedException {

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					for(int i=0;i<3;i++){
						System.out.println("take:"+SynchronousQueueTest.queue.take());
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					for(int i=0;i<3;i++){
						SynchronousQueueTest.queue.put("aaa"+i);
						System.out.println("put成功：aaa"+i);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}).start();
		
	}
}
