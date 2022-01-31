package com.zy.test1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Tank {// ��̹�˽��з�װ
	private int x, y;
	// ��ȡ̹�˷���
	private Dir dir = Dir.DOWN;
	// ̹��ÿ���ƶ��ľ��룬����Ϊ10
	private static final int SPEED = 5;
	// ����һ����̹�˾�ֹ�ı���
	private boolean moving = true;
	//����̹���Ƿ���ı���,Ĭ���Ǵ��״̬����Ϊtrue
	private boolean living = true;
	//����̹�˵Ŀ�͸�
//	public static int WIDTH = ResourceMgr.tankU.getWidth();
//	public static int HEIGHT = ResourceMgr.tankU.getHeight();
	//���强��̹�˺͵з�̹�˵Ŀ�͸�,��ΪͼƬ��С������һ�����Զ���һ����
	public static int WIDTH = ResourceMgr.goodTankU.getWidth();
	public static int HEIGHT = ResourceMgr.goodTankU.getHeight();
	//��ȡTakeFrame�Ķ���
	private TakeFrame tf = null;
	//����һ���ж��ҷ����ǵз���ö��
	private Group group = Group.BAD;
	//����������
	private Random random = new Random();
	//ͨ����������������Rectangle���д���
	Rectangle rect = new Rectangle();
	
	public Tank(int x, int y, Dir dir, Group group,TakeFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		this.tf = tf;
		//ͨ����������������Rectangle���д���
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
		if(!living){//���livingΪfalse�ˣ�˵��̹�˹���,��Դ�������
			tf.tanks.remove(this);
		}
		//��̹�˵�ģ�ͻ���ͼƬ
		//g.drawImage(ͼƬ,x,y,������)
		switch (dir) {
		//����ǰ��g.drawImage(ResourceMgr.tankL, x, y, null);
		//������Ŀ���ʽ���ж���Ҫ������̹�˵�ģ�ͻ��ǵз�̹�˵�ģ��
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
//		g.setColor(Color.YELLOW);//��̹�����óɻ�ɫ
//		// �����Ĭ�������Ͻǵ�λ��Ϊ0������Ϊx�ᣬ����Ϊy��
//		// ���԰�Graphics����һֻ���ʣ�fillRect(x,y,��,��);
//		g.fillRect(x, y, 50, 50);
//		g.setColor(c);
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
		//��Ӹ��ҷ�̹���ƶ���Ч���߳�
//		if(this.group == Group.GOOD)new Thread(()->new Audio("audio/tank_move.wav").play()).start();
		//�õз�̹����������ӵ�
		if(this.group == Group.BAD&&(random.nextInt(100)>95))this.fire();
		//�õз�̹������ƶ�
		if(this.group == Group.BAD && random.nextInt(100)>95)randomDir();
		//��������̹�˱߽���ķ���
		boundsCheck();
		//��ʱ�޸�Rectangle�������λ����Ϣ
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

	public void fire() {//�������ר�����ӵ���
		//���һ���ӵ�
//		tf.bullet = new Bullet(x, y, dir,tf);
		//������ӵ������λ�ã�̹�˵��м�λ�ã�
		int bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
		int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
		//���ӵ�����һ�������У������ӾͿ�����Ӷ���ӵ���
		tf.bullets.add(new Bullet(bX, bY, dir, this.group,tf));
		//��Ӹ��ҷ�̹�˷����ӵ���Ч���߳�
		if(this.group == Group.GOOD)new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
	}

	public void die() {//������������������̹�˱�Ϊ����״̬
		living = false;
	}
}
