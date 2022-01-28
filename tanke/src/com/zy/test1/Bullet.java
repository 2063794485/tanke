package com.zy.test1;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet {//�ӵ���
	//�ӵ���Ҫ���ٶȣ�λ�ã���С������
	private static final int SPEED = 10 , WIDTH = 20, HEIGHT = 20;//�ٶȣ�����
	private int x,y;//�ӵ���λ������
	private Dir dir;//����ö��
	private boolean live = true;//�ӵ��Ƿ���
	TakeFrame tf = null;//��ȡTakeFrame�Ķ���
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
		
		Color c = g.getColor();//��ȡ��ǰ���ʵ���ɫ
		g.setColor(Color.RED);//����������Ϊ��ɫ
		g.fillRect(x, y, WIDTH, HEIGHT);//����һ����x��yλ�ã���СΪWIDTH, HEIGHT������
		move();//�ӵ����ƶ�����
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
