package j9htsd;

public class Test {
	public static String output = "";
	
	public static void foo(int i ){
		try{
			if(i == 1 ){
				throw new Exception();
			}
			output +="1";
		}catch(Exception e){
			output += "2";
			return ;
		}finally{
			output += "3";
		}
		output +="4";
	}
	
	//输出 13423
	//知识点：有return 也会执行 finally
	public static void main(String[] args) {
		foo(0);
		foo(1);
		System.out.println(output);
	}
	
}
