package test;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

public class ImageTest {//junit是专门做测试用的

	@Test
	public void test() {
		try {
			//读取图片，一般使用img2的方式，但图片必须放在src下，路径相对于src写相对路径
//			BufferedImage imge = ImageIO.read(new File("src/images/bulletD.gif"));
//			assertNotNull(imge);
			BufferedImage img2 = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
			assertNotNull(img2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
