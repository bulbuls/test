package com.juc.thread;

import java.util.concurrent.BlockingQueue;

public class ConsumeThread implements Runnable{
	private BlockingQueue<String> queue;
	public ConsumeThread(BlockingQueue<String> queue){
		this.queue = queue;
	}
	
	@Override
	public void run() {
		try {
			System.out.println(Thread.currentThread().getName()+":"+queue.take());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
