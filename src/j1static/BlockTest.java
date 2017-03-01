/**   
 * Copyright © 2015 北京恒泰实达科技发展有限公司. All rights reserved.
 * 项目名称：tech00Java
 * 描述信息: 
 * 创建日期：2015年12月23日 下午6:30:52 
 * @author malitao
 * @version 
 */
package j1static;

import org.junit.Test;

/** 
 *  
 * 创建日期：2015年12月23日 下午6:30:52 
 * @author malitao
 */
public class BlockTest {

	@Test
	public void test() {
//		fail("Not yet implemented");
		Block.fun();
		Block.fun1();
		Block b = new Block();
		b.fun2();
		f();
	}
	
	public static void f(){
		Block.fun();
	}

}
