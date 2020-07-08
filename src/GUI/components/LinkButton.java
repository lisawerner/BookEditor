package GUI.components;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import GUI.theme.ThemeList;
import global.UserSettings;

public class LinkButton extends JButton{
	private static final long serialVersionUID = 1L;
	
	public LinkButton(String text, ActionListener a) {
		setText(text);
		addActionListener(a);
		
		Font font = getFont();
		Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		setFont(font.deriveFont(attributes));
	
		setFont(this.getFont().deriveFont((float) UserSettings.getInstance().getTextareaFontSize()));
		
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
		} else {

		}
	}

}
