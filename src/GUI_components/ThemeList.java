package GUI_components;

import java.awt.Color;
import java.util.ArrayList;

public class ThemeList {
	
	public static Theme currentTheme = null;
	
	public final static Theme BluePhoenixTheme = new Theme("Blue Phoenix", Color.WHITE, new Color(0,145,255),
			Color.WHITE, new Color(0,91,166),
			Color.WHITE, new Color(0,91,166), new Color(165,45,0), new Color(115,195,255), Color.WHITE,
			new Color(0,145,255),
			new Color(255,75,0));
	
	public final static Theme RedPhoenixTheme = new Theme("Red Phoenix", Color.WHITE, new Color(255,75,0),
			Color.WHITE, new Color(165,50,0), 
			Color.WHITE, new Color(255,145,0), new Color(0,91,166), new Color(255,220,115), new Color(0,91,166),
			Color.GRAY,
			new Color(0,60,105));

	public static ArrayList<Theme> getThemes() {
		ArrayList<Theme> completeList = new ArrayList<Theme>();
		completeList.add(BluePhoenixTheme);
		completeList.add(RedPhoenixTheme);
		return completeList;
	}
	
	public static void setCurrentTheme(Theme newTheme) {
		currentTheme = newTheme;
	}

}
