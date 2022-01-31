package com.zy.test1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.imageio.ImageIO;

public class Bullet {// �ӵ���
	// �ӵ���Ҫ���ٶȣ�λ�ã���С������
//	private static final int SPEED = 10, WIDTH = 20, HEIGHT = 20;// �ٶȣ�����
	private static final int SPEED = PropertyMgr.getInt("bulletSpeed");;
	public static int WIDTH = ResourceMgr.bulletD.getWidth();
	public static int HEIGHT = ResourceMgr.bulletD.getHeight();
	private int x, y;// �ӵ���λ������
	private Dir dir;// ����ö��
	private boolean living = true;// �ӵ��Ƿ���
	TakeFrame tf = null;// ��ȡTakeFrame�Ķ���
	//����һ���ж��ҷ����ǵз���ö��
	private Group group = Group.BAD;
	//��װ��Rectangle���������
	Rectangle rect = new Rectangle(); 
	public Bullet(int x, int y, Dir dir, Group group,TakeFrame tf) {
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

	public void paint(Graphics g) {
		if (!living) {//���livingΪfalse�ˣ�˵���ӵ�����Դ�������
			tf.bullets.remove(this);
		}

		// ���ӵ���ģ�ͻ���ͼƬ
		// g.drawImage(ͼƬ,x,y,������)
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

		// Color c = g.getColor();//��ȡ��ǰ���ʵ���ɫ
		// g.setColor(Color.RED);//����������Ϊ��ɫ
		// g.fillRect(x, y, WIDTH, HEIGHT);//����һ����x��yλ�ã���СΪWIDTH, HEIGHT������
		move();// �ӵ����ƶ�����
	}

	private void move() {// �ӵ����ƶ�����
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
		//�����ƶ���ʱ�޸�Rectangle�������λ����Ϣ
		rect.x = this.x;
		rect.y = this.y;
		
		if (x < 0 || y < 0 || x > tf.GAME_WIDTH || y > tf.GAME_HEIGHT)
			living = false;
	}

	public void collideWith(Tank tank) {
		//�ж�����ӵ���̹����ͬһ��Ӫ�Ļ���ֱ��return������ȥ�ж��Ƿ���ײ
		if(this.group == tank.getGroup())return;
		//Rectangle(x, y, ��, ��)��������һ���������࣬����������þ��Ƕ���һ�����ε�����
		//�����ж��ӵ��Ƿ���ײ����̹��
		//�ӵ��ľ���
//		Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
//		//̹�˵ľ���
//		Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), tank.WIDTH, tank.HEIGHT);
		//��Ϊÿ�μ����ײʱ��Ҫnew����������Ϊ�����ײ��ͨ��Ƕ��ѭ�������Ǿ������new������2m*n�ζ���
		//Ϊ���Ż������԰�Rectangle�����װ���ӵ���̹������
		
//		if(rect1.intersects(rect2)){
		if(rect.intersects(tank.rect)){
			//intersects����������������ж��������Ƿ��ཻ������ཻ�򷵻�true
			//���������������ֱ���̹���������ӵ������ķ���
			tank.die();
			this.die();
			//�����̹�˱�ը������λ��
			int ex = tank.getX()+ Tank.WIDTH/2 - Expload.WIDTH/2;
			int ey = tank.getY()+ Tank.HEIGHT/2 - Expload.HEIGHT/2;
			//���ӵ���̹����ײʱ���б�ը
			tf.exploads.add(new Expload(ex, ey, tf));
		}
	}

	private void die() {//�������������������ӵ���Ϊ����״̬
		this.living = false;
	}
}
