/**   
 * Copyright © 2015 北京恒泰实达科技发展有限公司. All rights reserved.
 * 项目名称：tech00Java
 * 描述信息: 
 * 创建日期：2015年12月23日 下午6:27:18 
 * @author malitao
 * @version 
 */
package j1static;

/** 
 *  
 * 创建日期：2015年12月23日 下午6:27:18 
 * @author malitao
 */
public class Block {
	
	static{
		System.out.println("Static Block ...");
	}
	public static void fun(){
		System.out.println("fun...");
	}
	public static void fun1(){
		System.out.println("fun1...");
	}
	public void fun2(){
		System.out.println("fun1...");
	}
	
}
