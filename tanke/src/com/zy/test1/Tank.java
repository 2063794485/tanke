package com.zy.test1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Tank {// 对坦克进行封装
	private int x, y;
	// 获取坦克方向
	private Dir dir = Dir.DOWN;
	// 坦克每次移动的距离，设置为10
	private static final int SPEED = 5;
	// 定义一个让坦克静止的变量
	private boolean moving = true;
	//定义坦克是否存活的变量,默认是存活状态，设为true
	private boolean living = true;
	//定义坦克的宽和高
//	public static int WIDTH = ResourceMgr.tankU.getWidth();
//	public static int HEIGHT = ResourceMgr.tankU.getHeight();
	//定义己方坦克和敌方坦克的宽和高,因为图片大小基本上一样就自定义一个了
	public static int WIDTH = ResourceMgr.goodTankU.getWidth();
	public static int HEIGHT = ResourceMgr.goodTankU.getHeight();
	//获取TakeFrame的东西
	private TakeFrame tf = null;
	//定义一个判断我方还是敌方的枚举
	private Group group = Group.BAD;
	//导入个随机数
	private Random random = new Random();
	//通过构造器给矩形类Rectangle进行传参
	Rectangle rect = new Rectangle();
	
	public Tank(int x, int y, Dir dir, Group group,TakeFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		this.tf = tf;
		//通过构造器给矩形类Rectangle进行传参
		rect.x = this.x;
		rect.y = this.y;
		rect.width = WIDTH;
		rect.height = HEIGHT;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
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
		if(!living){//如果living为false了，说明坦克挂了,资源该清除了
			tf.tanks.remove(this);
		}
		//将坦克的模型换成图片
		//g.drawImage(图片,x,y,监听器)
		switch (dir) {
		//将以前的g.drawImage(ResourceMgr.tankL, x, y, null);
		//换成三目表达式，判断是要画己方坦克的模型还是敌方坦克的模型
		case LIFT:
			g.drawImage(group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
			break;
		case UP:
			g.drawImage(group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
			break;
		case RIGHT:
			g.drawImage(group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
			break;
		case DOWN:
			g.drawImage(group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
			break;
		default:
			break;
		}
		
//		Color c = g.getColor();
//		g.setColor(Color.YELLOW);//将坦克设置成黄色
//		// 计算机默认在左上角的位置为0，往右为x轴，往下为y轴
//		// 可以把Graphics当做一只画笔，fillRect(x,y,宽,高);
//		g.fillRect(x, y, 50, 50);
//		g.setColor(c);
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
		//添加个我方坦克移动音效的线程
//		if(this.group == Group.GOOD)new Thread(()->new Audio("audio/tank_move.wav").play()).start();
		//让敌方坦克随机发射子弹
		if(this.group == Group.BAD&&(random.nextInt(100)>95))this.fire();
		//让敌方坦克随机移动
		if(this.group == Group.BAD && random.nextInt(100)>95)randomDir();
		//创建个让坦克边界检测的方法
		boundsCheck();
		//随时修改Rectangle矩形类的位置信息
		rect.x = this.x;
		rect.y = this.y;
	}

	private void boundsCheck() {
		if(this.x<=2)this.x = 2;
		if(this.y<=32)this.y = 32;
		if(this.x >= (tf.GAME_WIDTH-this.WIDTH-2))this.x = tf.GAME_WIDTH-this.WIDTH-2;
		if(this.y >= (tf.GAME_HEIGHT-this.HEIGHT-2))this.y = tf.GAME_HEIGHT-this.HEIGHT-2;
	}

	private void randomDir() {
		this.dir = Dir.values()[random.nextInt(4)];
	}

	public void fire() {//这个方法专门造子弹的
		//添加一颗子弹
//		tf.bullet = new Bullet(x, y, dir,tf);
		//计算出子弹发射的位置（坦克的中间位置）
		int bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
		int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
		//将子弹放入一个集合中，这样子就可以添加多个子弹了
		tf.bullets.add(new Bullet(bX, bY, dir, this.group,tf));
		//添加个我方坦克发射子弹音效的线程
		if(this.group == Group.GOOD)new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
	}

	public void die() {//如果调用了这个方法则将坦克变为死亡状态
		living = false;
	}
}
