package testpractice;

import java.util.HashMap;

public class HashMapTest {
	
	
	public void mapingTest(String values){
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("K", 1000);
		map.put("L", 100000);
		map.put("CR", 10000000);
		
		if(values.contains("K")){
			System.out.println(map.get("K")*Integer.parseInt(values.split("K")[0]));
		}
		else if(values.contains("L")){
			System.out.println(map.get("L")*Integer.parseInt(values.split("L")[0]));
		}
		else if(values.contains("CR")){
			System.out.println(map.get("CR")*Integer.parseInt(values.split("CR")[0]));
		}
		
		
	}
	
	
	public static void main(String[] args) {
		
		HashMapTest obj = new HashMapTest();
		obj.mapingTest("50L");
		obj.mapingTest("6CR");
		obj.mapingTest("7K");
		
	
	}

}
