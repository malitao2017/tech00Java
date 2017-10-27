package ja10jd;

public class Jdtest {

	public static void main(String[] args) {
//		intIntegeger();
		hash();
	}
	//基础类型和包装类的
	public static void intIntegeger(){
		System.out.println("基础类型和包装类的区别");
		int a = 1;
		Integer b = new Integer(1);
		System.out.println(a==b.intValue());
		System.out.println(a==b);
		System.out.println(b.equals(a));
	}
	//hash
	public static void hash(){
		int i = 190;
		Integer in = new Integer(190);
		String sh = "string";
		Jdtest jd = new Jdtest();
		System.out.println("字符串的hashcode："+sh.hashCode());
		System.out.println("包装类的hashcode："+in.hashCode());
		System.out.println("int的hashcode："+i);
		System.out.println("对象的hashcode："+jd.hashCode());
	}
}
