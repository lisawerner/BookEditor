package GUI.components;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

public class PageBody extends JScrollPane {
	private static final long serialVersionUID = 1L;
	
	private JPanel panel_viewport;
	private TransparentPanel my_center;

	public PageBody() {
		setOpaque(false);
		
		panel_viewport = new JPanel();
		panel_viewport.setLayout(new BorderLayout(0, 0));
		
		JViewport viewport = new JViewport();
		viewport.setOpaque(false);
		setViewportView(viewport);
		viewport.setView(panel_viewport);
		setViewportBorder(BorderFactory.createEmptyBorder());
		setBorder(BorderFactory.createEmptyBorder());

		my_center = new TransparentPanel();
		my_center.setLayout(new BoxLayout(my_center, BoxLayout.PAGE_AXIS));
		panel_viewport.add(my_center, BorderLayout.NORTH);
		
		changeTheme();
	}
	
	private void changeTheme() {
		if(ThemeList.currentTheme != null) {
			if(ThemeList.currentTheme.darkTheme) {
				panel_viewport.setBackground(ThemeList.currentTheme.darkBackgroundColor);
			} else {				
				panel_viewport.setBackground(ThemeList.currentTheme.lightBackgroundColor);
			}
			revalidate();
			repaint();
		} else {
			//System.out.println("Change Theme in Component to: Default");
		}
	}
	
	public void addCard(Card structureCard) {
		my_center.add(structureCard);
		Component structureGab = Box.createVerticalStrut(20);
		my_center.add(structureGab);
	}
	
	public void setFooter(Component footer) {
		panel_viewport.add(footer, BorderLayout.SOUTH);
		revalidate();
		repaint();
	}

}
