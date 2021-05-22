package GUI_components;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.Component;
import javax.swing.Box;

public class Page extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private static final String FRONT_TAG = "<html><div style='text-align: left;'><font size=\"5\">     ";
	private static final String BACK_TAG = "</size></div></html>";
	private final String my_title;
	private TitledBorder titledBorder;
	private final PageBody my_body;

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
		
		my_body = new PageBody();
		add(my_body, BorderLayout.CENTER);
		
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
			revalidate();
			repaint();
		}
	}
	
	public void addCard(Card newStructureCard) {
		my_body.addCard(newStructureCard);
	}
		
	public void setMenu(PageMenu newMenu) {
		add(newMenu, BorderLayout.EAST);
	}
	
	public void setFooter(Component footer) {
		my_body.setFooter(footer);
	}

}
