package GUI.components;

import GUI.theme.ThemeList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class Page extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private static final String FRONT_TAG = "<html><div style='text-align: left;'><font size=\"5\">     ";
	private static final String BACK_TAG = "</size></div></html>";
	private final String my_title;
	private TitledBorder titledBorder;

	private final JPanel panel_viewport;
	private final TransparentPanel my_center;

	public Page(String pageHeader) {
		setLayout(new BorderLayout(20, 20));
		my_title = pageHeader;
		titledBorder = BorderFactory.createTitledBorder(FRONT_TAG + my_title + BACK_TAG);
		setBorder(titledBorder);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		add(horizontalStrut, BorderLayout.NORTH);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		add(verticalStrut, BorderLayout.WEST);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		add(verticalStrut_1, BorderLayout.EAST);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		add(horizontalStrut_1, BorderLayout.SOUTH);

		JScrollPane my_body = new JScrollPane();
		add(my_body, BorderLayout.CENTER);
		my_body.setOpaque(false);
		
		panel_viewport = new JPanel();
		panel_viewport.setLayout(new BorderLayout(10, 10));
		
		JViewport viewport = new JViewport();
		viewport.setOpaque(false);
		my_body.setViewportView(viewport);
		viewport.setView(panel_viewport);
		my_body.setViewportBorder(BorderFactory.createEmptyBorder());
		my_body.setBorder(BorderFactory.createEmptyBorder());

		my_center = new TransparentPanel();
		my_center.setLayout(new BoxLayout(my_center, BoxLayout.PAGE_AXIS));
		panel_viewport.add(my_center, BorderLayout.CENTER);
		
		changeTheme();
	}
	
	private void changeTheme() {
		if(ThemeList.currentTheme != null) {
			Border coloredBorder = BorderFactory.createLineBorder(ThemeList.currentTheme.headerBackColor);
			titledBorder = BorderFactory.createTitledBorder(coloredBorder, FRONT_TAG + my_title + BACK_TAG);
			if(ThemeList.currentTheme.darkTheme) {				
				titledBorder.setTitleColor(ThemeList.currentTheme.darkForegroundColor);
			} else {
				titledBorder.setTitleColor(ThemeList.currentTheme.lightForegroundColor);
			}
			this.setBorder(titledBorder);
			if(ThemeList.currentTheme.darkTheme) {
				panel_viewport.setBackground(ThemeList.currentTheme.darkBackgroundColor);
			} else {				
				panel_viewport.setBackground(ThemeList.currentTheme.lightBackgroundColor);
			}
			revalidate();
			repaint();
		}
	}
	
	public void addCard(Card structureCard) {
		my_center.add(structureCard);
		Component structureGab = Box.createVerticalStrut(20);
		my_center.add(structureGab);
	}
		
	public void setMenu(PageMenu newMenu) {
		add(newMenu, BorderLayout.EAST);
	}
	
	public void setHeader(Component header) {
		panel_viewport.add(header, BorderLayout.NORTH);
		revalidate();
		repaint();
	}
	
	public void setFooter(Component footer) {
		panel_viewport.add(footer, BorderLayout.SOUTH);
		revalidate();
		repaint();
	}

}
