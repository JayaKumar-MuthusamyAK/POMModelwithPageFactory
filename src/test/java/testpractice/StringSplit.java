package testpractice;

public class StringSplit {
	
	
	public static void main(String[] args) {
		
		String text = "- Rs. 2495";
		//String text1 =text.replaceAll(",", "");
		String Text2 = text.replaceAll(",", "").split("- Rs. ")[1];
		System.out.println(Text2);
	}

}
