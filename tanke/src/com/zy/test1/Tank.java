package com.zy.test1;

import java.awt.Color;
import java.awt.Graphics;

public class Tank {// ��̹�˽��з�װ
	private int x, y;
	// ��ȡ̹�˷���
	private Dir dir = Dir.DOWN;
	// ̹��ÿ���ƶ��ľ��룬����Ϊ10
	private static final int SPEED = 10;
	// ����һ����̹�˾�ֹ�ı���
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
		g.setColor(Color.YELLOW);//��̹�����óɻ�ɫ
		// �����Ĭ�������Ͻǵ�λ��Ϊ0������Ϊx�ᣬ����Ϊy��
		// ���԰�Graphics����һֻ���ʣ�fillRect(x,y,��,��);
		g.fillRect(x, y, 50, 50);
		g.setColor(c);
		// x+=20;
		// y+=20;
		move();
	}

	private void move() {//��װһ�������ж��Ƿ������ƶ����������û������˵��û�ƶ�����ʱ̹�˾Ͳ���
		if (!moving)
			return;
		// ͨ��dir��ֵ�жϴ�ʱ�������ķ��������ĸ�����ģ��ڶ��䷽��ٿ�̹��������
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
		//���һ���ӵ�
//		tf.bullet = new Bullet(x, y, dir,tf);
		//���ӵ�����һ�������У������ӾͿ�����Ӷ���ӵ���
		tf.bullets.add(new Bullet(this.x, this.y, dir, tf));
	}
}
