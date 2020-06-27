package GUI.components;

public class MenuUtil {
	
	public static String clipToLength(String text){
		//TODO: W needs more space, then i, so name has cut by 17 instead of 25 letters! Maybe can get string-spacelength instead of letter-count
		return text.length() > 17 ? text.substring(0,15) + "..." : text;
	}
}
