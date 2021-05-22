package GUI.theme;

import java.awt.*;

public class Theme {
	
	public String themeName;
	public boolean darkTheme; //Otherwise lightTheme!
	
	public Color headerFontColor;
	public Color headerBackColor;
	
	public Color lightBackgroundColor;
	public Color darkBackgroundColor;
	public Color lightForegroundColor;
	public Color darkForegroundColor;
	
	public Color menuFontColor;
	public Color menuBackColor;
	public Color menuButtonFont;
	public Color menuButtonBack;
	public Color menuListButtonFont;
	
	public Color cardTitleFont;
	public Color cardTitleBack;
	
	//FontColor for RadioButtons, LinkButton, Checkbox
	public Color actionFontColor; 
	public Color darkActionFontColor;
	//BorderColor for TextFields, TextAreas...
	public Color actionBorderColor;
	//FontColor for SimpleLabel
	public Color simpleTextFontColor;
	
	public Color warningTutorialCardLayout;
	
		
	public Theme(String newThemeName, Color headerFontColorNew, Color newHeaderBackColor,
			Color newBackgroundColor, Color newForegroundColor, Color newDarkBackgroundColor, Color newDarkForegroundColor,
			Color newMenuFontColor, Color newMenuBackColor, Color newMenuButtonFont, Color newMenuButtonBack, Color newMenuListButtonFont,
			Color newCardTitleBackColor, Color newCardTitleFontColor,
			Color newActionBorderColor, Color newDarkActionFontColor,
			Color newWarningTutorialCardLayout) {
		themeName = newThemeName;
		headerFontColor = headerFontColorNew;
		headerBackColor = newHeaderBackColor;
		
		lightBackgroundColor = newBackgroundColor;
		lightForegroundColor = newForegroundColor;
		darkBackgroundColor = newDarkBackgroundColor;
		darkForegroundColor = newDarkForegroundColor;
		
		menuFontColor = newMenuFontColor;
		menuBackColor = newMenuBackColor;
		menuButtonBack = newMenuButtonBack;
		menuButtonFont = newMenuButtonFont;
		menuListButtonFont = newMenuListButtonFont;
		
		cardTitleFont = newCardTitleFontColor;
		cardTitleBack = newCardTitleBackColor;
		
		actionFontColor = headerBackColor;
		darkActionFontColor = newDarkActionFontColor;
		simpleTextFontColor = Color.darkGray;
		actionBorderColor = newActionBorderColor;
		
		warningTutorialCardLayout = newWarningTutorialCardLayout;
	}

}
