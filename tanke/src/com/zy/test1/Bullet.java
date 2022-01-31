package com.zy.test1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.imageio.ImageIO;

public class Bullet {// 子弹类
	// 子弹需要：速度，位置，大小，方向
//	private static final int SPEED = 10, WIDTH = 20, HEIGHT = 20;// 速度，宽，高
	private static final int SPEED = PropertyMgr.getInt("bulletSpeed");;
	public static int WIDTH = ResourceMgr.bulletD.getWidth();
	public static int HEIGHT = ResourceMgr.bulletD.getHeight();
	private int x, y;// 子弹的位置坐标
	private Dir dir;// 方向枚举
	private boolean living = true;// 子弹是否存活
	TakeFrame tf = null;// 获取TakeFrame的东西
	//定义一个判断我方还是敌方的枚举
	private Group group = Group.BAD;
	//封装个Rectangle矩形类对象
	Rectangle rect = new Rectangle(); 
	public Bullet(int x, int y, Dir dir, Group group,TakeFrame tf) {
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

	public void paint(Graphics g) {
		if (!living) {//如果living为false了，说明子弹的资源该清除了
			tf.bullets.remove(this);
		}

		// 将子弹的模型换成图片
		// g.drawImage(图片,x,y,监听器)
		switch (dir) {
		case LIFT:
			g.drawImage(ResourceMgr.bulletL, x, y, null);
			break;
		case UP:
			g.drawImage(ResourceMgr.bulletU, x, y, null);
			break;
		case RIGHT:
			g.drawImage(ResourceMgr.bulletR, x, y, null);
			break;
		case DOWN:
			g.drawImage(ResourceMgr.bulletD, x, y, null);
			break;
		default:
			break;
		}

		// Color c = g.getColor();//获取当前画笔的颜色
		// g.setColor(Color.RED);//将画笔设置为红色
		// g.fillRect(x, y, WIDTH, HEIGHT);//画出一个在x，y位置，大小为WIDTH, HEIGHT的物体
		move();// 子弹的移动方法
	}

	private void move() {// 子弹的移动方法
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
		//随着移动随时修改Rectangle矩形类的位置信息
		rect.x = this.x;
		rect.y = this.y;
		
		if (x < 0 || y < 0 || x > tf.GAME_WIDTH || y > tf.GAME_HEIGHT)
			living = false;
	}

	public void collideWith(Tank tank) {
		//判断如果子弹与坦克是同一阵营的话就直接return掉，不去判断是否碰撞
		if(this.group == tank.getGroup())return;
		//Rectangle(x, y, 宽, 高)，这类是一个“区域”类，它的最大作用就是定义一个矩形的区域
		//方便判断子弹是否碰撞到了坦克
		//子弹的矩形
//		Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
//		//坦克的矩形
//		Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), tank.WIDTH, tank.HEIGHT);
		//因为每次检测碰撞时都要new两个对象，因为检测碰撞是通过嵌套循环检测的那就相对于new对象是2m*n次对象
		//为了优化，可以把Rectangle对象封装到子弹和坦克类中
		
//		if(rect1.intersects(rect2)){
		if(rect.intersects(tank.rect)){
			//intersects这个方法的作用是判断两区域是否相交，如果相交则返回true
			//创建两个方法，分别让坦克死亡和子弹死亡的方法
			tank.die();
			this.die();
			//计算出坦克爆炸的中心位置
			int ex = tank.getX()+ Tank.WIDTH/2 - Expload.WIDTH/2;
			int ey = tank.getY()+ Tank.HEIGHT/2 - Expload.HEIGHT/2;
			//在子弹与坦克碰撞时进行爆炸
			tf.exploads.add(new Expload(ex, ey, tf));
		}
	}

	private void die() {//如果调用了这个方法则将子弹变为死亡状态
		this.living = false;
	}
}
