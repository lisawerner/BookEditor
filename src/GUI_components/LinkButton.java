package GUI_components;

import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class LinkButton extends JButton{
	private static final long serialVersionUID = 1L;
	
	
	public LinkButton(String text) {
		setText(text);
		
		Font font = getFont();
		Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		setFont(font.deriveFont(attributes));
		
		changeTheme();
	}
	
	private void changeTheme() {
		if(ThemeList.currentTheme != null) {
			if(ThemeList.currentTheme.darkTheme) {
				setBorder(BorderFactory.createEmptyBorder());
				setBackground(ThemeList.currentTheme.darkBackgroundColor);
				setForeground(ThemeList.currentTheme.darkActionFontColor);
			} else {				
				setBorder(BorderFactory.createEmptyBorder());
				setBackground(ThemeList.currentTheme.lightBackgroundColor);
				setForeground(ThemeList.currentTheme.actionFontColor);
			}
			revalidate();
			repaint();
		}
	}

}
