package GUI.components;

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
		} else {
//			System.out.println("Change Theme in Component to: Default");
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

	//TODO: Alle JLabel sollten etwas größere Schrift haben (nicht viel, aber bisschen! :D )
	//TODO: Alle JLabel sollten automatisch in <html> </html> Tags eingeschloßen werden :D

}
