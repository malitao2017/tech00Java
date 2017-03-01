package j5Collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Use {

	public static void main(String[] args) {
//		3) 反转(Reverse)
//    	使用Reverse方法可以根据元素的自然顺序 对指定列表按降序进行排序。
//		Collections.reverse(list)
		List<Double> list = new ArrayList<Double>();
		double array[] = {112, 111, 23, 456, 231 };
		for (int i = 0; i < array.length; i++) {
			list.add(new Double(array[i]));
		}
		for (int i = 0; i < array.length; i++) {
			System.out.println(list.get(i));
		}
		Collections. reverse(list);
		System.out.println("排序前后对比：");
		for (int i = 0; i < array.length; i++) {
		  System.out.println(list.get(i));
		}
		
	}
	
	
	
}
