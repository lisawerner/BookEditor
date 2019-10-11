package GUI_components;

import java.awt.Color;

public class Theme {
	
	public String themeName;
	
	public Color headerFontColor;
	public Color headerBackColor;
	
	public Color backgroundColor;
	public Color foregroundColor;
	
	public Color menuFontColor;
	public Color menuBackColor;
	public Color menuButtonFont;
	public Color menuButtonBack;
	public Color menuListButtonFont;
	
	public Color cardTitleFont;
	public Color cardTitleBack;
	
	public Color actionFontColor; //FontColor for Radiobuttons, BorderColor for Textfields, ...
	public Color actionBorderColor;
	public Color simpleTextFontColor;
	
	public Color warningTutorialCardLayout;
	
		
	public Theme(String newThemeName, Color headerFontColorNew, Color newHeaderBackColor,
			Color newBackgroundColor, Color newForegroundColor,
			Color newMenuFontColor, Color newMenuBackColor, Color newMenuButtonFont, Color newMenuButtonBack, Color newMenuListButtonFont,
			Color newActionBorderColor,
			Color newWarningTutorialCardLayout) {
		themeName = newThemeName;
		headerFontColor = headerFontColorNew;
		headerBackColor = newHeaderBackColor;
		
		backgroundColor = newBackgroundColor;
		foregroundColor = newForegroundColor;
		
		menuFontColor = newMenuFontColor;
		menuBackColor = newMenuBackColor;
		menuButtonBack = newMenuButtonBack;
		menuButtonFont = newMenuButtonFont;
		menuListButtonFont = newMenuListButtonFont;
		
		cardTitleFont = foregroundColor;
		cardTitleBack = menuButtonBack;
		
		actionFontColor = headerBackColor;
		simpleTextFontColor = Color.darkGray;
		actionBorderColor = newActionBorderColor;
		
		warningTutorialCardLayout = newWarningTutorialCardLayout;
	}

}
