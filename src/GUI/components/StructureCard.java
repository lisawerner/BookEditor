package GUI.components;

import GUI.theme.ThemeList;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class StructureCard extends Card {
	private static final long serialVersionUID = 1L;
	
	private static final String FRONT_TAG = "<html><div style='text-align: left;'><font size=\"4\">     ";
	private static final String BACK_TAG = "</size></div></html>";
	private JLabel lblCardTitle;
	private TransparentPanel my_body;
	
	public StructureCard(String cardTitle, TransparentPanel newBody) {
		createCard(cardTitle);
		setBody(newBody);
		
		changeTheme();
	}
	
	private void createCard(String cardTitle) {
		setLayout(new BorderLayout(10, 10));
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		add(horizontalStrut, BorderLayout.SOUTH);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		add(verticalStrut, BorderLayout.WEST);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		add(verticalStrut_1, BorderLayout.EAST);
		
		lblCardTitle = new JLabel(FRONT_TAG + cardTitle + BACK_TAG);
		add(lblCardTitle, BorderLayout.NORTH);
		
		my_body = new TransparentPanel();
		my_body.setLayout(new BorderLayout(10, 10));
		add(my_body, BorderLayout.CENTER);
	}
	
	private void changeTheme() {
		if(ThemeList.currentTheme != null) {
			setBorder(BorderFactory.createLineBorder(ThemeList.currentTheme.cardTitleBack));
			lblCardTitle.setForeground(ThemeList.currentTheme.cardTitleFont);
			lblCardTitle.setBackground(ThemeList.currentTheme.cardTitleBack);
			lblCardTitle.setOpaque(true);
			revalidate();
			repaint();
		} else {
			setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		}
	}
	
	public void setBody(Component newBody) {
		my_body.add(newBody, BorderLayout.NORTH);
		revalidate();
		repaint();
	}
}
