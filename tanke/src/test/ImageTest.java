package test;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

public class ImageTest {//junit��ר���������õ�

	@Test
	public void test() {
		try {
			//��ȡͼƬ��һ��ʹ��img2�ķ�ʽ����ͼƬ�������src�£�·�������srcд���·��
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
