package com.zy.test1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Expload {// ר�Ŵ�ű�ը��̬ͼƬ
	public static int WIDTH = ResourceMgr.exploads[0].getWidth();
	public static int HEIGHT = ResourceMgr.exploads[0].getHeight();
	private int x, y;// �ӵ���λ������
//	private boolean living = true;// �ӵ��Ƿ���
	TakeFrame tf = null;// ��ȡTakeFrame�Ķ���
	private int step = 0;//����ͼƬ�����˵ڼ�����
	public Expload(int x, int y, TakeFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.tf = tf;
		//���һ����ը��Ч���߳�
		new Thread(()->new Audio("audio/explode.wav").play()).start();
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
		//����ը�����������ͼƬ������
		g.drawImage(ResourceMgr.exploads[step++], x, y, null);
		//���˵step���ڵ���ͼƬ����ĳ����ˣ�˵��һ�α�ը�Ѿ�����ˣ����Խ���ɾ����
		if(step >= ResourceMgr.exploads.length)
//			step = 0;
			//�ȴ����ը��������ը�������
			tf.exploads.remove(this);
	}



}
