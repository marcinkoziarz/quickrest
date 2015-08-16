package pl.koziarz.quickrest;

/**
 * Some simple utility methods used in QuickRest
 * @author Marcin Koziarz <marcin@koziarz.pl>
 */
public class QuickRestUtils {
	
	
	/**
	 * Return abbreviated string to maxlen length. Resulted string has no more than
	 * maxlimit characters (including appended appendIfLimit string).
	 * 
	 * Examples:
	 * abbreviate("abcdefghij", 20, "..."); // returns "abcdefghij"
	 * abbreviate("abcdefghij", 10, "..."); // returns "abcdefghij"
	 * abbreviate("abcdefghij",  9, "..."); // returns "abcdef..."
	 * abbreviate("abcdefghij",  5, null);  // returns "abcde"
	 * 
	 * @param str string to be limited
	 * @param maxlen 
	 * @param appendIfLimit
	 * @return string with length not greater than maxlen with last few characters replaced with appendIfLimit
	 */
	public static String abbreviate(String str, int maxlen, String appendIfLimit) {
		if( appendIfLimit == null )
			appendIfLimit="";
		if( str.length() > maxlen ) {
			return str.substring(0,maxlen-appendIfLimit.length())+appendIfLimit;
		}
		return str;
	}
}
