package com.juc.thread;

import java.util.concurrent.BlockingQueue;

public class ProductThread implements Runnable{
	private BlockingQueue<String> queue;
	public ProductThread(BlockingQueue<String> queue){
		this.queue = queue;
	}
	
	@Override
	public void run() {
		for(int i=0;i<100;i++){
			try {
				queue.put(i+"");
				if(i==0){
					System.out.println("开始生产数据");
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
