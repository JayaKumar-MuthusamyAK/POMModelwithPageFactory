package testpractice;

import org.apache.commons.lang3.StringUtils;

public class StringSplit {
	
	
	public static void main(String[] args) {
		
		String text = "- Rs. 2495";
		//String text1 =text.replaceAll(",", "");
		String Text2 = text.replaceAll(",", "").split("- Rs. ")[1];
		System.out.println(Text2);
		
		int[] selectproductNumber = {2,4,6,8};
		
		for(int i=0; i< selectproductNumber.length;i++){
			System.out.println(selectproductNumber[i]);
			test(i);
		}
		
		System.out.println("------------------");
		String name ="loginemailtxtboxerrmsg|loginpasswdtxtboxerrmsg|afterloginusername";
		if(name.contains("|")){
			int countnum= StringUtils.countMatches(name, "|");
			for(int i=0; i<=countnum;i++){
				System.out.println(name.split("\\|")[i]);
			}
		}
		else{
			System.out.println("no");
		}
		
		
	}

	private static void test(int i) {
		 if((i+1)<=1){
			 System.out.println(i+1);
		 }
		 else{
			 System.out.println("not equals to 1");
		 }
		
	}

}
