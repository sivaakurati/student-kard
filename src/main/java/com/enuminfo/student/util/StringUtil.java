/**
 * 
 */
package com.enuminfo.student.util;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author Kumar
 */
public class StringUtil {

	private static final Random RANDOM = new SecureRandom();
	private static final int PASSWORD_LENGTH = 8;
	
	public static String generatePassword() {
		String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789@#";
		String randomPassword = "";
		for (int i=0; i<PASSWORD_LENGTH; i++) {
			int index = (int)(RANDOM.nextDouble()*letters.length());
			randomPassword += letters.substring(index, index+1);
		}
		return randomPassword;
	}
	
	public static String toTitleCase(String input) {
		if (input==null || "".equalsIgnoreCase(input)) return "";
		input = input.toLowerCase();
		StringBuilder titleCase = new StringBuilder();
	    boolean nextTitleCase = true;
	    for (char c : input.toCharArray()) {
	        if (Character.isSpaceChar(c)) {
	            nextTitleCase = true;
	        } else if (nextTitleCase) {
	            c = Character.toTitleCase(c);
	            nextTitleCase = false;
	        }
	        titleCase.append(c);
	    }
	    return titleCase.toString();
	}
}
