package j7map;

import java.util.HashMap;
import java.util.Map;

public class KeyRepeat {

	public static void main(String[] args) {
		//key重复的时候是保存最后一次的值
		Map<String, String> map = new HashMap<String,String>();
		map.put("keys", "value1");
		map.put("keys", "value2");
		for(String key : map.keySet()){
			System.out.println("值为："+key);
			System.out.println(map.get(key));
		}
		
	}
}
