package util;

import java.util.Random;

public class BankTransaction {
	private static Random rnd = new Random(System.currentTimeMillis());
	
	public static String processTransaction() {
		String referenceNumber = "";
		int digits;
		
		float chanceOfFailure = rnd.nextFloat();
		
		if(chanceOfFailure <= 0.70) { // 70% chance that the bank transaction was successful
			System.out.println("Your transaction was accpeted!");
			
			for(int i = 5; i > 0; i--) {
				digits = 10000 + rnd.nextInt(90000); // generates a random 5 digit value
				referenceNumber += String.valueOf(digits); // concatenating random digits
			}
			
			return referenceNumber;
		}
		else { // 30% chance that the bank transaction was not successful
			System.out.println("Your transaction was denied!");
			
			return "-1";
		}
		
	} // end of isTransaction method
	
} // end of BankTransaction class

