package j9htsd;

public class Test2 {
	
	public static void StringReplace(String text){
		text = text.replace("j", "l");
	}
	public static void bufferReplace(StringBuffer text){
		//这两行效果一样
		text.append("c");
//		text = text.append("c");
	}
	//知识点：String是重新构建对象；StringBuffer是传入了地址
	public static void main(String[] args) {
		String s = new String("java");
		StringBuffer sb = new StringBuffer("java");
		StringReplace(s);
		bufferReplace(sb);
		System.out.println(s+sb);
	}
	
}
