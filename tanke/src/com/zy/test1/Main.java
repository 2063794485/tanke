package com.zy.test1;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		TakeFrame tf = new TakeFrame();
		
		//��ʼ���з�̹��
		for (int i = 0; i < 5; i++) {
			 tf.tanks.add(new Tank(50+i*80,200,Dir.DOWN, Group.BAD,tf));
		}
		//���������������߳�
		new Thread(()->new Audio("audio/war1.wav").loop()).start();
		
		while(true){
			//д����ѭ���������Զ�ˢ���ƶ�
			Thread.sleep(50);
			tf.repaint();
		}
		
	
	}

}
