package testpractice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;

public class StringSplit {
	
	
	public static void main(String[] args) throws ParseException {
		
		String text = "Total: Rs. 2,495";
		//String text1 =text.replaceAll(",", "");
		String Text2 = text.replaceAll("Total: ", "").replaceAll(",", "").split("Rs. ")[1];
		System.out.println(Text2);
		String Text4 =  " - 359 Items".replaceAll("Items", "").replaceAll("-", "").split("Rs. ")[0].trim();
		System.out.println(Text4);
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
		
	
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		String text1 = "07h:5m".replace("h", "");
		Date date = sdf.parse(text1);
		cal.setTime(date);
		System.out.println(sdf.format(date.getTime()));
		String expe = sdf.format(date.getTime());
		StringBuffer str = new StringBuffer(expe);
		str.insert(5, "m").deleteCharAt(3);
		str.insert(2, "h").deleteCharAt(0);
		str.deleteCharAt(2).insert(2, " ");
		System.out.println(str);
		
		String text4 = "Chromozome Men Charcoal Thermal Long Johns TH-03";
		String text5 ="Thermal Long Johns";
		System.out.println(text4.contains(text5));
		
		String var = "(3 Items)".replace("(", "").replace(")", "").trim();
		
		System.out.println(var.split(" ")[0]);
		List<String> list = new LinkedList<String>();
		list.add(0, "Chromozome Men Charcoal Thermal Long Johns TH-03");
		list.add(1, "Park Avenue Brown Slim Fit Single-Breasted Formal Suit");
		list.add(2, "Raymond Brown Single-Breasted Bangala Suit");
		list.add(3, "Wintage Navy Single-Breasted Formal Suit");
		for(int i=0; i<list.size(); i++){
			if(list.get(i).contains("Thermal Long Johns")){
				System.out.println("Matching");
			}
			
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
