package j8lg;

public class Run2 {
	
//	关于静态变量2结论：
//	1.new多态类会执行父类
//	2.最先运行父子类的静态块
//	3.先运行父类的构造函数
//	4.每次创建子类都会先运行父类构造函数
	public static void main(String[] args) {
		A2 a = new B2();
		a = new B2();
	}
}

class A2{
	static{
		System.out.println("1");
	}
	public A2(){
		System.out.println("2");
	}
}

class B2 extends A2{
	static{
		System.out.println("a");
	}
	public B2(){
		System.out.println("b");
	}
}
