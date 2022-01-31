package com.zy.test1;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {//专门处理配置文件类
	//因为是处理配置文件，里面的东西都要放入静态池(使用static修饰),在类加载的时候就和类一起加载了
	//Properties这个类是专门用来读取java中的配置文件的，里面存储的格式都是键值对的格式
	static Properties props = new Properties();
	
	static{
		try {
			//将配置文件加载到这个Properties类中
			props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Object getObj(String key) {//定义一个获取value的方法
		if (props == null)return null;
		return props.get(key);
	}
	public static int getInt(String key) {//定义一个获取value为int类型的方法
//		if (props == null)return null;
		return Integer.parseInt(props.get(key).toString());
	}
	public static String getString(String key) {//定义一个获取value为String类型的方法
		if (props == null)return null;
		return props.get(key).toString();
	}
	
	
//	public static void main(String[] args) {
//		System.out.println(PropertyMgr.props.get("initTankCount"));
//	}
}
