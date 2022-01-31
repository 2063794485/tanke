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

	// 坦克进行了封装，需要一个坦克就new一个就行了
	Tank myTank = new Tank(200, 600, Dir.DOWN, Group.GOOD,this);
	//new一个子弹
//	Bullet bullet = new Bullet(200, 200, Dir.DOWN, this);
	//设置游戏的界面大小，让其保持不变
	static final int GAME_WIDTH = 1080, GAME_HEIGHT = 760;
	//创建一个链表，专门装子弹
	List<Bullet> bullets = new ArrayList<>();
	//创建一个装敌方坦克的链表
	List<Tank> tanks = new ArrayList<>();
	//创建一个装爆炸效果的链表
	List<Expload> exploads = new ArrayList<>();
	//new一个爆炸效果测试
//	Expload e = new Expload(200, 300, this);
	public TakeFrame() {
		// 设置窗口大小
		setSize(GAME_WIDTH, GAME_HEIGHT);
		// 将出口设置为不可改变其大小
		setResizable(false);
		// 设置标题
		setTitle("坦克大战");
		// 添加一个win监听器，让右上角那个删除起作用
		addWindowListener(new WindowAdapter() {
			// WindowAdapter是一个内部类，他实现了WindowListener，
			// 重写windowClosing方法当鼠标点击删除键是就进入这个方法里面
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// 将窗口显示出来
		setVisible(true);
		// 添加一个键盘监听
		addKeyListener(new MyKeyListener());
	}
	//双缓冲解决闪烁问题，其实就是repaint调用paint时，会先调用update，重写update方法，从中截获update
	//利用重写的update解决闪烁问题的原理：先将该画的东西（坦克，子弹）画在内存的图片中，图片的大小设置和游戏界面大小一致
	//把内存中的图片一次性画到屏幕上（内存的内容复制到显存里）
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
	
	// 重写paint方法，当窗口每一次重构时都会重新调用一次paint方法
	@Override
	public void paint(Graphics g) {
		//因为背景是黑色的，想要看到获取子弹的数量，将主题换成白色
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.drawString("子弹的数量："+bullets.size(), 10, 60);
		g.drawString("敌方坦克的数量："+tanks.size(), 10, 80);
		g.drawString("爆炸的数量："+exploads.size(), 10, 100);
		g.setColor(c);
		
		myTank.paint(g);
//		bullet.paint(g);
		for (int i = 0; i < bullets.size(); i++) {
			//在这里不能使用迭代器，因为后面会对子弹进行一个回收，防止内存溢出只能使用普通for循环
			//使用迭代器或者增强for循环会报错，因为子弹是装在这个链表内的，在遍历时删除元素，
			//这就涉及到集合一个很经典的报错了，详细见笔记第11章集合中ListIterator迭代器这一小节
			bullets.get(i).paint(g);
		}
		for (int i = 0; i < tanks.size(); i++) {
			//画出坦克
			tanks.get(i).paint(g);
		}
		for (int i = 0; i < exploads.size(); i++) {
			//画出爆炸效果
			exploads.get(i).paint(g);
		}
		//将子弹与坦克进行比较，看是否发生碰撞，如果发生则将对应的子弹与坦克删除
		for (int i = 0; i < bullets.size(); i++) {
			for (int j = 0; j < tanks.size(); j++) {
				//创建一个collideWith碰撞检测方法，看是否会碰撞上
				bullets.get(i).collideWith(tanks.get(j));
			}
			
		}
	}

	
	// 创建一个内部类，专门接收键盘的监听
	class MyKeyListener extends KeyAdapter {
		boolean bL = false;// 左
		boolean bU = false;// 上
		boolean bR = false;// 右
		boolean bD = false;// 下

		@Override
		public void keyPressed(KeyEvent e) {
			// 键盘按下会调用这个方法
			// x+=30;
			// repaint();//repaint方法的作用是让系统去掉用paint方法
			// getKeyCode方法获取键盘按下的键对应的值
			int key = e.getKeyCode();
			// 判断键盘按下的键是否与需要的对应
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
			// 通过键盘上按键的开关给dir赋默认值
			setMainTankDir();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// 键盘松开会调用这个方法
			// getKeyCode方法获取键盘松开的键对应的值
			int key = e.getKeyCode();
			// 判断键盘松开的键是否与需要的对应
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
				myTank.fire();//将坦克发射子弹封装到这个方法，松开ctrl时发射一颗子弹
				break;
			default:
				break;
			}
			// 通过键盘上按键的开关给dir赋默认值
			setMainTankDir();
		}

		private void setMainTankDir() {
			if (!bL && !bU && !bR && !bD)//都为false说明此时按键没有被触动
				myTank.setMoving(false);
			else {
				myTank.setMoving(true);
				// 通过键盘上按键的开关给dir赋默认值，哪个方向的开关打开了就赋值给哪个
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
