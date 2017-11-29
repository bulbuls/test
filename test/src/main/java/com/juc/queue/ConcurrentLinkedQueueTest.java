package com.juc.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
/**
 * ConcurrentLinkedQueue
 * 特点：无界，链表,非阻塞，基于CAS
 * 描述：新增时，判断是否是队尾，cas更新如果成功返回，如果失败继续循环更新；
 *     删除时，判断是否是队头，cas更新如果成功返回，如果失败继续循环更新；
 * @author TF017477
 *
 */
public class ConcurrentLinkedQueueTest {
	
	private final static ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();
	
	/**
	 * 添加元素
	 * 有三种新增元素的方法，都能新增元素，但新增方式有细微差别
	 * @throws InterruptedException 
	 */
	public void insert() throws InterruptedException{
		//offer：新增成功返回true，失败返回false
		queue.offer("a");
		//add：在本队列中，底层调用offer方法进行实现。新增失败时会抛出异常 IllegalStateException("Queue full");
		queue.add("a");
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
		
		//底层调用poll，返回对象，如果没有取到元素则抛出异常 NoSuchElementException();
		queue.remove();

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
		ConcurrentLinkedQueueTest test = new ConcurrentLinkedQueueTest();
		test.insert();
		test.delete();
	}

}
