package com.juc.queue;

import java.util.concurrent.LinkedTransferQueue;

import com.juc.thread.ConsumeThread;
import com.juc.thread.ProductThread;
/**
 * 基于cas的无锁队列
 * 特点：链表，基于cas无锁操作
 * 描述：这个队列的特点是当队列为空时，某个消费者线程会在队列尾部创建一个空节点并阻塞，
 * 其他消费线程正常阻塞，如此一来，当生存者生产一个对象这个对象会立即交给第一个被阻塞的消费者线程
 * 
 * CAS:compare and swap(比较与交换) eg：我认为V的值应该为A，如果是，那么将V的值更新为B，否则不修改并告诉V的值实际为多少
 * @author TF017477
 *
 */
public class LinkedTransferQueueTest {
	private final static LinkedTransferQueue<String> queue = new LinkedTransferQueue<String>();

	public static void main(String[] args) throws InterruptedException {

		new Thread(new ProductThread(queue)).start();
		Thread.sleep(3000L);
		queue.put("a");
		System.out.println("1");
		/*for(int i=0;i<100;i++){
			Thread thread = new Thread(new ConsumeThread(queue));
			thread.setName("线程"+i);
			thread.start();
		}*/
		
	}
}