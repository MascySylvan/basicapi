package com.example.basicapi.commons;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class StringUtil {

	private static Map<Character, Character> specialCharMap = new HashMap<Character, Character>();

	static {
		char[] scArr = new char[] { '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '+', '=', '-', '~', '`', '{',
				'[', '}', ']', '|', '\\', ':', ';', '"', '\'', '<', ',', '>', '.', '?', '/' };

		for (char c : scArr) {
			specialCharMap.put(c, c);
		}
	} // End method
	
	public static String generateUUID() {  
			
		UUID uniqueKey = UUID.randomUUID();
		
		return uniqueKey.toString().replace("-", "");
	}

	public static boolean hasSpecialCharacter(String str) {

		if (str == null || str.trim().isEmpty()) {
			return false;
		}

		String s = str;

		for (int i = 0; i < s.length(); i++) {

			if (specialCharMap.get(s.charAt(i)) != null) {
				return true;
			}
		}

		return false;
	}
	
	public static boolean hasSpecialCharacterWithExceptions(String str, char[] chars) {
		
		if (str == null || str.trim().isEmpty() ) {
			return false;
		}
		
		String s = str;

		for (int i = 0; i < s.length(); i++) {
			
			boolean found = false;
			for (int j = 0; j < chars.length; j++) {
				if(chars[j] == s.charAt(i)) {
					found = true;
					break;
				}
			}
			
			if(found) {
				continue;
			}
			
			if (specialCharMap.get(s.charAt(i) ) != null) {
				return true;
			}
		}

		return false;
	}

	public static boolean hasLowerCaseLetter(String str) {
		if (str == null || str.trim().isEmpty()) {
			return false;
		}

		String s = str;

		for (int i = 0; i < s.length(); i++) {

			if (Character.isLetter(s.charAt(i)) && Character.isLowerCase(s.charAt(i))) {
				return true;
			}
		}

		return false;
	}

	public static boolean hasUpperCaseLetter(String str) {
		if (str == null || str.trim().isEmpty()) {
			return false;
		}

		String s = str;

		for (int i = 0; i < s.length(); i++) {

			if (Character.isLetter(s.charAt(i)) && Character.isUpperCase(s.charAt(i))) {
				return true;
			}
		}

		return false;
	}

	public static boolean hasLetter(String str) {

		if (str == null || str.trim().isEmpty()) {
			return false;
		}

		String s = str;

		for (int i = 0; i < s.length(); i++) {

			if (Character.isLetter(s.charAt(i))) {
				return true;
			}
		}

		return false;
	}

	public static boolean hasDigit(String str) {

		if (str == null || str.trim().isEmpty()) {
			return false;
		}

		String s = str;

		for (int i = 0; i < s.length(); i++) {

			// If we find a non-digit character we return false.
			if (Character.isDigit(s.charAt(i))) {
				return true;
			}
		}

		return false;
	}

	public static long stringToLong(String str) {

		if (str == null || str.trim().isEmpty()) {
			return 0;
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < str.length(); i++) {
			// If we find a non-digit character we return false.
			if (Character.isDigit(str.charAt(i))) {
				sb.append(str.charAt(i));
			}
		}

		if (sb.length() > 0) {
			return Long.valueOf(sb.toString());
		}

		return 0;
	}

	public static boolean isSpecialCharsExists(String str) {

		if (str == null || str.isEmpty()) {
			return false;
		}

		for (char c : str.toCharArray()) {

			if (Character.isWhitespace(c) && !Character.isSpaceChar(c)) {
				return true;
			}
		}

		return false;
	}

	public static int countExtendedAsciiChars(String str) {

		if (str == null || str.isEmpty()) {
			return 0;
		}

		int counter = 0;

		for (char c : str.toCharArray()) {
			if (Integer.valueOf(c) >= 128) {
				counter++;
			}
		}

		return counter;
	}

	public static String paddRightZero(int numOfZero, String str) {

		if (str != null) {
			int n = numOfZero - str.length();

			for (int x = 0; x < n; x++) {
				str = str + "0";
			}
		}

		return str;
	}

	public static String paddLeftZero(int numOfZero, String str) {

		if (str == null) {
			str = "";
		}

		int n = numOfZero - str.length();

		for (int x = 0; x < n; x++) {
			str = "0" + str;
		}

		return str;
	}

	public static String paddLeftSpace(int numOfSpace, String str) {

		if (str == null) {
			str = "";
		}

		int n = numOfSpace - str.length();

		for (int x = 0; x < n; x++) {
			str = " " + str;
		}

		return str;
	}

	public static String paddRightSpace(int numOfSpace, String str) {

		if (str == null) {
			str = "";
		}

		int n = numOfSpace - str.length();

		for (int x = 0; x < n; x++) {
			str = str + " ";
		}

		return str;
	}

	public static boolean isNullOrEmpty(String str) {
		if (isNull(str) || isEmpty(str)) {
			return true;
		}

		return false;
	}

	public static boolean isEmpty(String str) throws NullPointerException {
		if (str.trim().isEmpty()) {
			return true;
		}

		return false;
	}

	public static boolean isNull(String str) {
		if (str == null) {
			return true;
		}

		return false;
	}

	public static boolean isNumeric(String str) {

		if (str == null || str.trim().isEmpty()) {
			return false;
		}

		String s = str;
		int dotCounter = 0;

		for (int i = 0; i < s.length(); i++) {
			// If we find a non-digit character we return false.
			if (s.charAt(i) == '.') {

				if (dotCounter > 0) {
					return false;
				}

				dotCounter++;
				continue;
			}

			if (!Character.isDigit(s.charAt(i))) {
				return false;
			}
		}

		return true;
	} // End method

	public static int countInstance(char x, String str) {
		int instance = 0;

		if (str == null || str.isEmpty()) {
			return instance;
		}

		for (char c : str.toCharArray()) {
			if (c == x) {
				instance++;
			}
		}

		return instance;
	}

	public static String paddRight(int numPadds, String padd, String str) {

		if (str == null) {
			str = "";
		}

		int n = numPadds - str.length();

		for (int x = 0; x < n; x++) {
			str = str + padd;
		}

		return str;
	}

	public static String paddLeft(int numPadds, String padd, String str) {

		if (str == null) {
			str = "";
		}

		int n = numPadds - str.length();

		for (int x = 0; x < n; x++) {
			str = padd + str;
		}

		return str;
	}

	public static String removeExtendedChars(String str) {

		if (str == null) {
			str = "";
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) < 32) {
				continue;
			}

			if (str.charAt(i) > 125) {
				continue;
			}

			sb.append(str.charAt(i));
		}

		return sb.toString().trim();
	} // End method

	public static String removeNonAlphaChars(String str) {

		if (str == null) {
			str = "";
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) < 65) {
				continue;
			}

			if (str.charAt(i) > 90) {
				if (str.charAt(i) < 97) {
					continue;
				}

				if (str.charAt(i) > 122) {
					continue;
				}
			}

			sb.append(str.charAt(i));
		}

		return sb.toString().trim();
	} // End method

	public static String removeNonAlphaNumericChars(String str) {

		if (str == null) {
			str = "";
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) < 65) {
				if (str.charAt(i) < 48) {
					continue;
				}

				if (str.charAt(i) > 57) {
					continue;
				}
			}

			if (str.charAt(i) > 90) {
				if (str.charAt(i) < 97) {
					continue;
				}

				if (str.charAt(i) > 122) {
					continue;
				}
			}

			sb.append(str.charAt(i));
		}

		return sb.toString().trim();
	} // End method

	public static String removeNonPathChars(String str) {

		if (str == null) {
			str = "";
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < str.length(); i++) {

			// Exclude dash and underscore
			if (str.charAt(i) == 45 || str.charAt(i) == 95) {
				sb.append(str.charAt(i));
				continue;
			}

			if (str.charAt(i) < 65) {
				if (str.charAt(i) < 48) {
					continue;
				}

				if (str.charAt(i) > 57) {
					continue;
				}
			}

			if (str.charAt(i) > 90) {
				if (str.charAt(i) < 97) {
					continue;
				}

				if (str.charAt(i) > 122) {
					continue;
				}
			}

			sb.append(str.charAt(i));
		}

		return sb.toString().trim();
	} // End method

	public static HashMap<String, Object> convertJsonToMap(String json) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
		};

		HashMap<String, Object> map = mapper.readValue(json, typeRef);

		return map;
	} // End method

	public static String convertMapToJson(HashMap<String, Object> map) throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		String json = "";

		// Convert map to JSON string
		json = mapper.writeValueAsString(map);
		json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);

		return json;
	} // End method

	public static String trimNumeric(String num) {
		if (num == null) {
			return null;
		}

		String result = "";
		boolean start = false;

		for (int i = 0; i < num.length(); i++) {
			if ((num.charAt(i) != '0') && (!start)) {
				start = true;
			}

			if (start) {
				result += num.charAt(i);
			}
		}

		return result;
	}

	public static String mask(char mask, int maskPercentage, String accountNumber) {
		if (accountNumber == null) {
			return null;
		}

		if (maskPercentage <= 0 || maskPercentage > 100) {
			return null;
		}

		int len = accountNumber.length();

		if (len < 2) {
			return accountNumber;
		}

		int percentageInt = Double.valueOf(Math.floor(Double.valueOf(len) * (Double.valueOf(maskPercentage) / 100)))
				.intValue();

		StringBuilder sb = new StringBuilder();

		for (int x = 0; x < percentageInt; x++) {
			sb.append(mask);
		}

		sb.append(accountNumber.substring(percentageInt));

		return sb.toString();
	} // End method

	public static String urlDecode(String str) {
		if (str == null) {
			return null;
		}

		String decodedString = "";

		if (str.trim().isEmpty()) {
			return decodedString;
		}

		try {
			decodedString = URLDecoder.decode(str, StandardCharsets.UTF_8.name());
		} catch (Exception e) {
		}

		return decodedString;
	}

	public static String urlEncode(String str) {
		if (str == null) {
			return null;
		}

		String encodedString = "";

		if (str.trim().isEmpty()) {
			return encodedString;
		}

		try {
			encodedString = URLEncoder.encode(str, StandardCharsets.UTF_8.name());
		} catch (Exception e) {
		}

		return encodedString;
	}

	public static boolean isValidUUID(String str) {

		if (str == null || str.trim().isEmpty() ) {
			return false;
		}

		String s = str;

		for (int i = 0; i < s.length(); i++) {

			if (specialCharMap.get(s.charAt(i)) != null) {
				return false;
			}
		}
		
		return (str.length() == 32);
	}
	
	public static boolean isValidEmail(String str) {
        if (str == null) {
            return false;
        }
        
        String strPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        
        Pattern pattern = Pattern.compile(strPattern);
        
        return pattern.matcher(str).matches();
    }
	
	public static String encodeString(String s) {
		return Base64.getEncoder().encodeToString(s.getBytes() );
	}
	
	public static String decodeString(String s) {
		return new String(Base64.getDecoder().decode(s) );
	}

	public static void main(String[] args) {
		 String input1 = "ezzatcasim@gmail.com";
		 System.out.println("\nTest Case 1:");
		 System.out.println("Input: " + input1 + " \t " + StringUtil.isValidEmail(input1) );
	        
		 String input2 = "ezzatcasim@robinsonsbank.com.ph";
		 System.out.println("\nTest Case 2:");
		 System.out.println("Input: " + input2 + " \t " + StringUtil.isValidEmail(input2) );
	        
	     String input3 = "    @  ";
	     System.out.println("\nTest Case 3:");
	     System.out.println("Input: " + input3 + " \t " + StringUtil.isValidEmail(input3) );
	        
	        
	     String input4 = "123123123@yahoo.com";
	     System.out.println("\nTest Case 4:");
	     System.out.println("Input: " + input4 + " \t " + StringUtil.isValidEmail(input4) );
	        
	     String input5 = "he@gmail.com@test.com";
	     System.out.println("\nTest Case 5:");
	     System.out.println("Input: " + input5 + " \t " + StringUtil.isValidEmail(input5) );
	        
	     String input6 = null;
	     System.out.println("\nTest Case 6:");
	     System.out.println("Input: " + input6 + " \t Result: " + StringUtil.isValidEmail(input6) );
	        
	     String input7 = "ezzatcasim@gmail.com.ph.ph.ph";
	     System.out.println("\nTest Case 7:");
	     System.out.println("Input: " + input7 + " \t Result: " + StringUtil.isValidEmail(input7) );
	     
	     String input8 = " ";
	     System.out.println("\nTest Case 8:");
	     System.out.println("Input: " + input8 + " \t Result: " + StringUtil.isValidEmail(input8) );
	     
	     input8 = "xyz@asd!com";
	     System.out.println("\nTest Case 9:");
	     System.out.println("Input: " + input8 + " \t Result: " + StringUtil.isValidEmail(input8) );
	     
	     input8 = "--@asd.com";
	     System.out.println("\nTest Case 10:");
	     System.out.println("Input: " + input8 + " \t Result: " + StringUtil.isValidEmail(input8) );
	     
	     input8 = "xyz@asd..com";
	     System.out.println("\nTest Case 11:");
	     System.out.println("Input: " + input8 + " \t Result: " + StringUtil.isValidEmail(input8) );
	}

}
