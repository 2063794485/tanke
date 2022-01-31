package com.zy.test1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Expload {// 专门存放爆炸动态图片
	public static int WIDTH = ResourceMgr.exploads[0].getWidth();
	public static int HEIGHT = ResourceMgr.exploads[0].getHeight();
	private int x, y;// 子弹的位置坐标
//	private boolean living = true;// 子弹是否存活
	TakeFrame tf = null;// 获取TakeFrame的东西
	private int step = 0;//计数图片画到了第几个了
	public Expload(int x, int y, TakeFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.tf = tf;
		//添加一个爆炸音效的线程
		new Thread(()->new Audio("audio/explode.wav").play()).start();
	}


	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	
	
	public void paint(Graphics g) {
		//将爆炸这个数组连续图片画出来
		g.drawImage(ResourceMgr.exploads[step++], x, y, null);
		//如果说step大于等于图片数组的长度了，说明一次爆炸已经完成了，可以进行删除了
		if(step >= ResourceMgr.exploads.length)
//			step = 0;
			//等待这个炸完后将这个爆炸给清空了
			tf.exploads.remove(this);
	}



}
