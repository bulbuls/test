package com.juc.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
/**
 * 队列
 * 基于链表的阻塞队列  LinkedBlockingQueueTest
 * 特点：有界,链表，阻塞，基于锁
 * 功能上和ArrayBlockingQueue别无二致，差别体现在底层实现差异上
 * 应用场景：大小几乎无限，所以需要考虑使用时队列堆积的情况
 * @author TF017477
 *
 */
public class LinkedBlockingQueueTest {

	private static LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();
	
	/**
	 * 添加元素
	 * 有三种新增元素的方法，都能新增元素，但新增方式有细微差别
	 * @throws InterruptedException 
	 */
	public void insert() throws InterruptedException{
		//offer：新增成功返回true，失败返回false
		queue.offer("a");
		//add：在本队列中，底层调用offer方法进行实现。新增失败时会抛出异常 IllegalStateException("Queue full");
		queue.add("b");
		//put：阻塞式新增，如果队列已满，此调用会处于阻塞状态
		queue.put("c");
		//------------------------------------------------------
		queue.add("d");
		queue.add("e");
		queue.add("f");
	}

	/**
	 * 删除元素
	 * 有三种新增元素的方法，都能新增元素，但新增方式有细微差别
	 * @throws InterruptedException 
	 */
	public void delete() throws InterruptedException{
		
		//poll：立即取出队列末尾的一个元素
		queue.poll();
		
		//取出末尾一个元素，如果没有则阻塞固定的一段时间
		queue.poll(10000, TimeUnit.MILLISECONDS);
		
		//底层调用poll，返回对象，如果没有取到元素则抛出异常 NoSuchElementException();
		queue.remove();

		//阻塞式获取数据
		queue.take();

		// 循环链表，删除匹配的元素；成功返回true，失败返回false
		queue.remove("d");
	}
	
	/**
	 * 预览
	 * 返回队尾的元素，但是并不从队列中删除它
	 */
	public void see(){
		//成功返回true，失败返回false
		queue.peek();
		//底层调用peek方法，成功返回true，失败抛出 NoSuchElementException
		queue.element();
	}
	
	/**
	 * 队列
	 * 导出元素，元素筛选
	 */
	public void tain(){
		List<String> list = new ArrayList<String>();
		
		//将数组中的元素排出到list中（可以用来做持久化）
		queue.drainTo(list);
		
		/**
		 * 取交集，只保留queue和list相同的元素
		 */
		queue.retainAll(list);
		/**
		 * 取补集，只保留queue和list不相同的元素
		 */
		queue.removeAll(list);
	}
	public static void main(String[] args) {
		
	}

}
