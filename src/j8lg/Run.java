package j8lg;

public class Run {
	
	//关于静态变量，不会涉及到多态，谁执行就运行谁的静态方法
	public static void main(String[] args) {
		A ab = new B();
		ab.f();
	}
}

class A{
	public static void f(){
		System.out.println("A");
	}
}

class B extends A{
	public static void f(){
		System.out.println("B");
	}
}