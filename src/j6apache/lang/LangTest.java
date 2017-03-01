package j6apache.lang;

import org.apache.commons.lang3.builder.EqualsBuilder;

public class LangTest {

	public static void main(String[] args) {
		//isEquals
		boolean flag = new EqualsBuilder()
                .append("4","4")
                .isEquals();
		System.out.println("是否相等："+flag);
		
	}
	
	
	
}
