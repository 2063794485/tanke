package com.zy.test1;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResourceMgr {//��Դ��������ר����������ͼƬ
	//̹��ͼƬ����������
//	public static BufferedImage tankL,tankU,tankR,tankD;
	//��̹��ͼƬ�������֣�����̹����з�̹�˲�ͬ
	public static BufferedImage goodTankL,goodTankU,goodTankR,goodTankD;
	public static BufferedImage badTankL,badTankU,badTankR,badTankD;
	//�ӵ�ͼƬ����������
	public static BufferedImage bulletL,bulletU,bulletR,bulletD;
	//̹�˱�ը�Ķ�̬ͼƬ,��Ϊһ����16�ţ������ø�������洢����
	public static BufferedImage[] exploads = new BufferedImage[16];
	static{
		try {
			//��ȡ̹�˵�ͼƬ�����丳ֵ
//			tankL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
//			tankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
//			tankR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankR.gif"));
//			tankD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankD.gif"));
			//����Щ�ÿ����̹��ͼƬ,ͼƬĬ�������ϵ�
			//��תͼƬ�Ĺ����࣬����ʹ��ImageUtil.rotateImage(ͼƬ����ת�ĽǶ�)���е���
//			tankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
//			tankL = ImageUtil.rotateImage(tankU, -90);
//			tankR = ImageUtil.rotateImage(tankU, 90);
//			tankD = ImageUtil.rotateImage(tankU, 180);
			//������̹�˺͵з�̹�˵�ͼƬ��ȡ��ȥ
			goodTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
			goodTankL = ImageUtil.rotateImage(goodTankU, -90);
			goodTankR = ImageUtil.rotateImage(goodTankU, 90);
			goodTankD = ImageUtil.rotateImage(goodTankU, 180);
			
			badTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
			badTankL = ImageUtil.rotateImage(badTankU, -90);
			badTankR = ImageUtil.rotateImage(badTankU, 90);
			badTankD = ImageUtil.rotateImage(badTankU, 180);
			
			//��ȡ�ӵ���ͼƬ�����丳ֵ
//			bulletL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
//			bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
//			bulletR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletR.gif"));
//			bulletD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
			bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
			bulletL = ImageUtil.rotateImage(bulletU, -90);
			bulletR = ImageUtil.rotateImage(bulletU, 90);
			bulletD = ImageUtil.rotateImage(bulletU, 180);
			//��ͼƬ����ѭ���洢����
			for (int i = 0; i < 16; i++) {
				exploads[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e"+(i+1)+".gif"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
