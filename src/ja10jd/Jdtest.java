package ja10jd;

public class Jdtest {

	public static void main(String[] args) {
		int a = 1;
		Integer b = new Integer(1);
		System.out.println(a==b.intValue());
		System.out.println(a==b);
		System.out.println(b.equals(a));
	}
}
