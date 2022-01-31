package com.zy.test1;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResourceMgr {//资源管理器，专门用来加载图片
	//坦克图片的上下左右
//	public static BufferedImage tankL,tankU,tankR,tankD;
	//将坦克图片做个区分，己方坦克与敌方坦克不同
	public static BufferedImage goodTankL,goodTankU,goodTankR,goodTankD;
	public static BufferedImage badTankL,badTankU,badTankR,badTankD;
	//子弹图片的上下左右
	public static BufferedImage bulletL,bulletU,bulletR,bulletD;
	//坦克爆炸的动态图片,因为一共有16张，所以用个数组给存储起来
	public static BufferedImage[] exploads = new BufferedImage[16];
	static{
		try {
			//读取坦克的图片，将其赋值
//			tankL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
//			tankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
//			tankR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankR.gif"));
//			tankD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankD.gif"));
			//更换些好看点的坦克图片,图片默认是向上的
			//旋转图片的工具类，可以使用ImageUtil.rotateImage(图片，旋转的角度)进行调用
//			tankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
//			tankL = ImageUtil.rotateImage(tankU, -90);
//			tankR = ImageUtil.rotateImage(tankU, 90);
//			tankD = ImageUtil.rotateImage(tankU, 180);
			//将己方坦克和敌方坦克的图片读取上去
			goodTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
			goodTankL = ImageUtil.rotateImage(goodTankU, -90);
			goodTankR = ImageUtil.rotateImage(goodTankU, 90);
			goodTankD = ImageUtil.rotateImage(goodTankU, 180);
			
			badTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
			badTankL = ImageUtil.rotateImage(badTankU, -90);
			badTankR = ImageUtil.rotateImage(badTankU, 90);
			badTankD = ImageUtil.rotateImage(badTankU, 180);
			
			//读取子弹的图片，将其赋值
//			bulletL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
//			bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
//			bulletR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletR.gif"));
//			bulletD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
			bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
			bulletL = ImageUtil.rotateImage(bulletU, -90);
			bulletR = ImageUtil.rotateImage(bulletU, 90);
			bulletD = ImageUtil.rotateImage(bulletU, 180);
			//将图片利用循环存储起来
			for (int i = 0; i < 16; i++) {
				exploads[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e"+(i+1)+".gif"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
