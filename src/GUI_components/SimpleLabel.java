package GUI_components;

import java.awt.Color;

import javax.swing.JLabel;

public class SimpleLabel extends JLabel{
	private static final long serialVersionUID = 1L;
	
	public SimpleLabel(String text) {
		super(text);
		
		changeTheme();
	}
	
	private void changeTheme() {
		if(ThemeList.currentTheme != null) {
			if(ThemeList.currentTheme.darkTheme) {
				setForeground(ThemeList.currentTheme.darkForegroundColor);
			}
			revalidate();
			repaint();
		}
	}
	
	public void setWarning(boolean activateWarning) {
		if(activateWarning) {
			if(ThemeList.currentTheme != null) {			
				setForeground(ThemeList.currentTheme.warningTutorialCardLayout);
			} else {
				setForeground(Color.RED);
			}
			revalidate();
			repaint();
		} else {
			changeTheme();
		}
	}

	//TODO: the font size in all Labels should be a little bit bigger (only a little bit! :D )
	//TODO: all label texts should be included from <html> </html> Tags automatically:D

}
