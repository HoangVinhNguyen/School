package com.school.admin.util;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class NamePersonUtils {

	public static String getFirstName(String fullname) {
		if (fullname != null) {
			String[] arrayName= fullname.split("\\s+");
			return arrayName[arrayName.length-1];
		}
		return null;
	}
	
	public static String getLastName(String fullname) {
		if (fullname != null) {
			String[] arrayName= fullname.split("\\s+");
			return arrayName[0];
		}
		return null;
	}

	public static String getMailName(String fullname) {
		if (fullname != null) {
			String normalName = deAccent(fullname).toLowerCase();
			String[] arrayName= normalName.split("\\s+");
			for (int i = 0; i < arrayName.length; i++) {
				if (i != arrayName.length - 1) {
					arrayName[i] = String.valueOf(arrayName[i].charAt(0));
				}
			}
			return String.join("", arrayName);
		}
		return null;
	}
	
	private static String deAccent(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD); 
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }
}
