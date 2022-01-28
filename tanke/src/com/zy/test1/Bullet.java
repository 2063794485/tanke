package com.zy.test1;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet {//子弹类
	//子弹需要：速度，位置，大小，方向
	private static final int SPEED = 10 , WIDTH = 20, HEIGHT = 20;//速度，宽，高
	private int x,y;//子弹的位置坐标
	private Dir dir;//方向枚举
	private boolean live = true;//子弹是否存活
	TakeFrame tf = null;//获取TakeFrame的东西
	public Bullet(int x, int y, Dir dir, TakeFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tf = tf;
	}
	public void paint(Graphics g) {
		if(!live){
			tf.bullets.remove(this);
		}
		
		Color c = g.getColor();//获取当前画笔的颜色
		g.setColor(Color.RED);//将画笔设置为红色
		g.fillRect(x, y, WIDTH, HEIGHT);//画出一个在x，y位置，大小为WIDTH, HEIGHT的物体
		move();//子弹的移动方法
	}
	private void move() {
		switch (dir) {
		case LIFT:
			x -= SPEED;
			break;
		case UP:
			y -= SPEED;
			break;
		case RIGHT:
			x += SPEED;
			break;
		case DOWN:
			y += SPEED;
			break;
		default:
			break;
		}
		if(x<0 || y<0 || x>tf.GAME_WIDTH || y>tf.GAME_HEIGHT) live =false;
	}
}
