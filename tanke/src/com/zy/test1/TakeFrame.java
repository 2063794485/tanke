package com.zy.test1;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TakeFrame extends Frame {

	// ̹�˽����˷�װ����Ҫһ��̹�˾�newһ��������
	Tank myTank = new Tank(200, 600, Dir.DOWN, Group.GOOD,this);
	//newһ���ӵ�
//	Bullet bullet = new Bullet(200, 200, Dir.DOWN, this);
	//������Ϸ�Ľ����С�����䱣�ֲ���
	static final int GAME_WIDTH = 1080, GAME_HEIGHT = 760;
	//����һ������ר��װ�ӵ�
	List<Bullet> bullets = new ArrayList<>();
	//����һ��װ�з�̹�˵�����
	List<Tank> tanks = new ArrayList<>();
	//����һ��װ��ըЧ��������
	List<Expload> exploads = new ArrayList<>();
	//newһ����ըЧ������
//	Expload e = new Expload(200, 300, this);
	public TakeFrame() {
		// ���ô��ڴ�С
		setSize(GAME_WIDTH, GAME_HEIGHT);
		// ����������Ϊ���ɸı����С
		setResizable(false);
		// ���ñ���
		setTitle("̹�˴�ս");
		// ���һ��win�������������Ͻ��Ǹ�ɾ��������
		addWindowListener(new WindowAdapter() {
			// WindowAdapter��һ���ڲ��࣬��ʵ����WindowListener��
			// ��дwindowClosing�����������ɾ�����Ǿͽ��������������
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// ��������ʾ����
		setVisible(true);
		// ���һ�����̼���
		addKeyListener(new MyKeyListener());
	}
	//˫��������˸���⣬��ʵ����repaint����paintʱ�����ȵ���update����дupdate���������нػ�update
	//������д��update�����˸�����ԭ���Ƚ��û��Ķ�����̹�ˣ��ӵ��������ڴ��ͼƬ�У�ͼƬ�Ĵ�С���ú���Ϸ�����Сһ��
	//���ڴ��е�ͼƬһ���Ի�����Ļ�ϣ��ڴ�����ݸ��Ƶ��Դ��
	Image offScreenImage = null;
	@Override
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	// ��дpaint������������ÿһ���ع�ʱ�������µ���һ��paint����
	@Override
	public void paint(Graphics g) {
		//��Ϊ�����Ǻ�ɫ�ģ���Ҫ������ȡ�ӵ��������������⻻�ɰ�ɫ
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.drawString("�ӵ���������"+bullets.size(), 10, 60);
		g.drawString("�з�̹�˵�������"+tanks.size(), 10, 80);
		g.drawString("��ը��������"+exploads.size(), 10, 100);
		g.setColor(c);
		
		myTank.paint(g);
//		bullet.paint(g);
		for (int i = 0; i < bullets.size(); i++) {
			//�����ﲻ��ʹ�õ���������Ϊ�������ӵ�����һ�����գ���ֹ�ڴ����ֻ��ʹ����ͨforѭ��
			//ʹ�õ�����������ǿforѭ���ᱨ����Ϊ�ӵ���װ����������ڵģ��ڱ���ʱɾ��Ԫ�أ�
			//����漰������һ���ܾ���ı����ˣ���ϸ���ʼǵ�11�¼�����ListIterator��������һС��
			bullets.get(i).paint(g);
		}
		for (int i = 0; i < tanks.size(); i++) {
			//����̹��
			tanks.get(i).paint(g);
		}
		for (int i = 0; i < exploads.size(); i++) {
			//������ըЧ��
			exploads.get(i).paint(g);
		}
		//���ӵ���̹�˽��бȽϣ����Ƿ�����ײ����������򽫶�Ӧ���ӵ���̹��ɾ��
		for (int i = 0; i < bullets.size(); i++) {
			for (int j = 0; j < tanks.size(); j++) {
				//����һ��collideWith��ײ��ⷽ�������Ƿ����ײ��
				bullets.get(i).collideWith(tanks.get(j));
			}
			
		}
	}

	
	// ����һ���ڲ��࣬ר�Ž��ռ��̵ļ���
	class MyKeyListener extends KeyAdapter {
		boolean bL = false;// ��
		boolean bU = false;// ��
		boolean bR = false;// ��
		boolean bD = false;// ��

		@Override
		public void keyPressed(KeyEvent e) {
			// ���̰��»�����������
			// x+=30;
			// repaint();//repaint��������������ϵͳȥ����paint����
			// getKeyCode������ȡ���̰��µļ���Ӧ��ֵ
			int key = e.getKeyCode();
			// �жϼ��̰��µļ��Ƿ�����Ҫ�Ķ�Ӧ
			switch (key) {
			case KeyEvent.VK_LEFT:
				bL = true;
				break;
			case KeyEvent.VK_UP:
				bU = true;
				break;
			case KeyEvent.VK_RIGHT:
				bR = true;
				break;
			case KeyEvent.VK_DOWN:
				bD = true;
				break;
			default:
				break;
			}
			// ͨ�������ϰ����Ŀ��ظ�dir��Ĭ��ֵ
			setMainTankDir();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// �����ɿ�������������
			// getKeyCode������ȡ�����ɿ��ļ���Ӧ��ֵ
			int key = e.getKeyCode();
			// �жϼ����ɿ��ļ��Ƿ�����Ҫ�Ķ�Ӧ
			switch (key) {
			case KeyEvent.VK_LEFT:
				bL = false;
				break;
			case KeyEvent.VK_UP:
				bU = false;
				break;
			case KeyEvent.VK_RIGHT:
				bR = false;
				break;
			case KeyEvent.VK_DOWN:
				bD = false;
				break;
			case KeyEvent.VK_CONTROL:
				myTank.fire();//��̹�˷����ӵ���װ������������ɿ�ctrlʱ����һ���ӵ�
				break;
			default:
				break;
			}
			// ͨ�������ϰ����Ŀ��ظ�dir��Ĭ��ֵ
			setMainTankDir();
		}

		private void setMainTankDir() {
			if (!bL && !bU && !bR && !bD)//��Ϊfalse˵����ʱ����û�б�����
				myTank.setMoving(false);
			else {
				myTank.setMoving(true);
				// ͨ�������ϰ����Ŀ��ظ�dir��Ĭ��ֵ���ĸ�����Ŀ��ش��˾͸�ֵ���ĸ�
				if (bL)
					myTank.setDir(Dir.LIFT);
				if (bU)
					myTank.setDir(Dir.UP);
				if (bR)
					myTank.setDir(Dir.RIGHT);
				if (bD)
					myTank.setDir(Dir.DOWN);
			}
		}
	}
}
