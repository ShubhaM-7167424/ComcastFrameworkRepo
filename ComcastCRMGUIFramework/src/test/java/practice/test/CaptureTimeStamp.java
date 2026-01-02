package practice.test;

import java.util.Date;

public class CaptureTimeStamp {

	public static void main(String[] args) {
		String date = new Date().toString();
//		date  = date.replace(" ", "_").replace(":", "_");
		
		System.out.println(date);
	}

}
