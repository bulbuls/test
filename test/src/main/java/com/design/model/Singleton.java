package com.design.model;

import java.util.Date;

/**
 * 单例模式
 * 确保在jvm中永远只存在一个实例
 * 1：私有化构造函数
 * 2：提供创建入口，并在入口处加上重复创建检测和并发检测
 * 缺点：可以通过反射的方式创建这个对象  
 * @author TF017477
 *
 */
public class Singleton {
	private Date time = null;
	private static Singleton singleton = null;
	//私有化构造函数 防止被别人实例化
	private Singleton(){
		time = new Date();
	}
	//构建单独的入口
	public static Singleton create(){
		//检测对象为空：如果对象不为空 则表示已经实例化过
		if(null == singleton){
			synchronized (Singleton.class) {
				//再次检查对象是否为空 ：为了防止多线程不同步问题
				if(null == singleton){
					singleton = new Singleton();
				}
			}
		}
		return singleton;
	}
	
	public Date getTime() {
		return time;
	}
	public static void main(String[] args) {
		
		Singleton a = Singleton.create();
		Singleton b = Singleton.create();
		Singleton c = Singleton.create();
		
		System.out.println(a.getTime().getTime());
		System.out.println(b.getTime().getTime());
		System.out.println(c.getTime().getTime());
	}
}
