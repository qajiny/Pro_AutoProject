package Utilities;

import java.util.Random;
public class Utils {
	
	// Creates Random no.
	public static Integer CreateRandomNo()
	{
		Random rg = new Random();
		
		Integer randomInt = rg.nextInt(1000);
		System.out.println("Generated : " + randomInt);
		return randomInt;
	}

}
