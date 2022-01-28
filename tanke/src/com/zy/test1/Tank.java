package com.zy.test1;

import java.awt.Color;
import java.awt.Graphics;

public class Tank {// 对坦克进行封装
	private int x, y;
	// 获取坦克方向
	private Dir dir = Dir.DOWN;
	// 坦克每次移动的距离，设置为10
	private static final int SPEED = 10;
	// 定义一个让坦克静止的变量
	private boolean moving = false;
	
	private TakeFrame tf = null;
	public Tank(int x, int y, Dir dir, TakeFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tf = tf;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.YELLOW);//将坦克设置成黄色
		// 计算机默认在左上角的位置为0，往右为x轴，往下为y轴
		// 可以把Graphics当做一只画笔，fillRect(x,y,宽,高);
		g.fillRect(x, y, 50, 50);
		g.setColor(c);
		// x+=20;
		// y+=20;
		move();
	}

	private void move() {//封装一个方法判断是否正在移动，如果键盘没按按键说明没移动，此时坦克就不动
		if (!moving)
			return;
		// 通过dir的值判断此时按键按的方向是往哪个方向的，在对其方向操控坦克往哪走
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
	}

	public void fire() {
		//添加一颗子弹
//		tf.bullet = new Bullet(x, y, dir,tf);
		//将子弹放入一个集合中，这样子就可以添加多个子弹了
		tf.bullets.add(new Bullet(this.x, this.y, dir, tf));
	}
}
