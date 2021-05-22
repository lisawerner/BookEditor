package GUI.components;

import GUI.theme.ThemeList;

import javax.swing.*;

public class SimpleScrollPane extends JScrollPane {
	private static final long serialVersionUID = 1L;

	public SimpleScrollPane(TransparentPanel newViewBody) {
		setOpaque(false);
		
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		JViewport viewport = new JViewport();
		viewport.setOpaque(false);
		setViewportView(viewport);
		viewport.setView(newViewBody);
		changeTheme();
	}
	
	private void changeTheme() {
		if(ThemeList.currentTheme != null) {
			if(ThemeList.currentTheme.darkTheme) {
				this.getViewport().setBackground(ThemeList.currentTheme.darkBackgroundColor);
			} else {
				this.getViewport().setBackground(ThemeList.currentTheme.lightBackgroundColor);
			}
			revalidate();
			repaint();
		}
	}

}
