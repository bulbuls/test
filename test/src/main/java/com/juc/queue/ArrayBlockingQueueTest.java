package com.juc.queue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 队列
 * 基于数组的阻塞队列  ArrayBlockingQueue
 * 特点：有界,数组，阻塞,基于锁
 * 实现方式
 * 	数组：Object[] items  
 * 	插入偏移量：putIndex
 *	取值偏移量：takeIndex
 *	元素总量：	count
 *  阻塞控制
 * 应用场景：由于大小固定，所以适合生产与消费速率相当的应用场景，不用担心队列堆积而导致内存溢出
 * @author TF017477
 *
 */
public class ArrayBlockingQueueTest {

	private static ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);

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

		/**
		 * 循环数组，删除匹配的元素；
		 * 此方法删除会移动数组，所以当队列特别长的时候，最好不要用这个方法
		 * 移动的方式：
		 * 	1：如果删除的元素正好在队尾，则将队尾设置为null即可，不用位移元素
		 *  2：如果删除的元素不在队尾，则需要将其后面的元素依次前移（消耗性能，不适合很长的队列）
		 * 成功返回true，失败返回false
		 */
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
	public static void main(String[] args) throws InterruptedException {
		ArrayBlockingQueueTest test = new ArrayBlockingQueueTest();
		test.insert();
		//test.delete();
		test.tain();
		Iterator<String> it = test.queue.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}

}
