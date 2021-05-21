package GUI_components;

import java.awt.Color;
import java.util.ArrayList;

public class ThemeList {
	
	//Color Theme: https://colorschemedesigner.com/csd-3.5/#
	//RGB to HEX: https://www.rgbtohex.net/
	//HEX to RGB: https://www.rapidtables.com/convert/color/hex-to-rgb.html
	//Check contrast: https://webaim.org/resources/contrastchecker/
	
	public static Theme currentTheme = null;
		
	public final static Theme BluePhoenixTheme = new Theme("Blue Phoenix", Color.WHITE, new Color(0,145,255),
			Color.WHITE, new Color(0,91,166), Color.DARK_GRAY, Color.WHITE,
			Color.WHITE, new Color(0,91,166), new Color(110,10,10), new Color(160,215,255), Color.WHITE,	//TODO: ButtonFontColor does not have enough contrast
			new Color(160,215,255), new Color(0,91,166),
			new Color(10,90,140), new Color(155, 235, 235),
			new Color(255,75,0));
	
	public final static Theme RedPhoenixTheme = new Theme("Red Phoenix", Color.WHITE, new Color(255,75,0),
			Color.WHITE, new Color(165,50,0), Color.DARK_GRAY, Color.WHITE,
			Color.WHITE, new Color(255,145,0), new Color(0,91,166), new Color(255,220,115), new Color(0,91,166),
			new Color(255,220,115), new Color(165,50,0),
			Color.GRAY, new Color(255,220,115),
			new Color(0,60,105));
	
	public final static Theme ADifferentWorldTheme = new Theme("A different World", Color.WHITE, new Color(172, 10, 10),
			Color.WHITE, new Color(165,50,0), Color.DARK_GRAY, Color.WHITE,
			Color.WHITE, new Color(40, 80, 125), new Color(0,0,0), new Color(100, 150, 210), Color.WHITE,
			new Color(90, 160, 70), new Color(25, 40, 20),
			Color.GRAY, new Color(165, 215, 105),
			new Color(200,195,140));
	
	public static ArrayList<Theme> getThemes() {
		ArrayList<Theme> completeList = new ArrayList<>();
		completeList.add(BluePhoenixTheme);
		completeList.add(RedPhoenixTheme);
		completeList.add(ADifferentWorldTheme);
		return completeList;
	}
	
	public static void setCurrentTheme(Theme newTheme) {
		currentTheme = newTheme;
	}

}
