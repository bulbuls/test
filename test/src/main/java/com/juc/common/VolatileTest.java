package com.juc.common;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * volatile：用于修改变量，被修饰的变量取值的时候能获取到最新的值；
 * 				volatile a=b
 * --------------------------------------------------
 * 				|	主存/副本刷新		|
 * --------------------------------------------------
 * 		线程A	:a	|		线程B	：a		|	线程C:a
 * 				|					|
 * --------------------------------------------------
 * 正常的线程模型：同一个变量生成多个副本分配给在不同线程，线程内的对变量的操作会刷新到主存中，主存再刷新其他副本。这中间产生的时间差产生线程不可见性问题。
 * 被volatile修饰的变量：每次读取变量的时候主导到主存中获得最新的值
 * 
 * 内存屏障:明确指令顺数，确保数据的可见性
 * 线程可见性：俩个线程A，B操作同一个变量c=0，A修改了c=1；B线程获取c的值；c=1
 * 原子性：俩个线程A，B操作同一个变量c=0，A修改了c+1；B线程修改c++；最终c=2
 * 注意: volatile仅仅用来保证该变量对所有线程的可见性，但不保证原子性!!
 * @author TF017477
 */
public class VolatileTest {

	private volatile int a;
	private volatile Integer b;
	public static void main(String[] args) {
		Map<String,String> map = new TreeMap();
		map.put("a", "A");
		map.put("b", "A");
		map.put("c", "A");
		map.put("d", "A");
		
		
		Set<String> set = new TreeSet();
		set.add("a");
		set.add("b");
		set.add("c");
		set.add("d");
	}

}
