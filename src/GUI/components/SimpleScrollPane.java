package GUI.components;

import javax.swing.JScrollPane;
import javax.swing.JViewport;

public class SimpleScrollPane extends JScrollPane {
	private static final long serialVersionUID = 1L;
	
	private TransparentPanel viewBody;
	private JViewport viewport;

	public SimpleScrollPane(TransparentPanel newViewBody) {
		setOpaque(false);
		
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		viewBody = newViewBody;
		
		viewport = new JViewport();
		viewport.setOpaque(false);
		setViewportView(viewport);
		viewport.setView(viewBody);
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
		} else {
			//System.out.println("Change Theme in Component to: Default");
		}
	}

}
