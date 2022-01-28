package com.zy.test1;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		TakeFrame tf = new TakeFrame();
		while(true){
			//写个死循环，让其自动刷新移动
			Thread.sleep(50);
			tf.repaint();
		}
		
	
	}

}
