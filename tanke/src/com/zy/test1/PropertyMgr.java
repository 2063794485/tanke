package com.zy.test1;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {//ר�Ŵ��������ļ���
	//��Ϊ�Ǵ��������ļ�������Ķ�����Ҫ���뾲̬��(ʹ��static����),������ص�ʱ��ͺ���һ�������
	//Properties�������ר��������ȡjava�е������ļ��ģ�����洢�ĸ�ʽ���Ǽ�ֵ�Եĸ�ʽ
	static Properties props = new Properties();
	
	static{
		try {
			//�������ļ����ص����Properties����
			props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Object getObj(String key) {//����һ����ȡvalue�ķ���
		if (props == null)return null;
		return props.get(key);
	}
	public static int getInt(String key) {//����һ����ȡvalueΪint���͵ķ���
//		if (props == null)return null;
		return Integer.parseInt(props.get(key).toString());
	}
	public static String getString(String key) {//����һ����ȡvalueΪString���͵ķ���
		if (props == null)return null;
		return props.get(key).toString();
	}
	
	
//	public static void main(String[] args) {
//		System.out.println(PropertyMgr.props.get("initTankCount"));
//	}
}
