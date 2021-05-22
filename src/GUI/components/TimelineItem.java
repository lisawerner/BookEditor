package GUI.components;

import GUI.theme.ThemeList;
import time.Timestamp;

import javax.swing.*;
import java.awt.*;

public class TimelineItem extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private final TransparentPanel my_body;
	private final SimpleLabel contactArrow;
	private final JLabel contactDate;
	private final JPanel topRightGab;
	private TransparentPanel panelOverBody;
	
	private static final String FRONT_TAG = "<html><font size=\"9\">";
	private static final String BACK_TAG = "</size></html>";

	public TimelineItem(boolean leftPosition, Timestamp timestamp) {
		setLayout(new BorderLayout(0, 0));
		
		String dateString = timestamp.toString();
		System.out.println(dateString);
		
		if(dateString.length() < 10) {
			String newDate;
			if(dateString.indexOf(".") < 2) {
				newDate = "0" + dateString.substring(0, 2); 
				dateString = dateString.substring(2);
			}else {
				newDate = dateString.substring(0, 3); 
				dateString = dateString.substring(3);
			}
			if(dateString.indexOf(".") < 2) {
				newDate += "0" + dateString.substring(0, 2); 
				dateString = dateString.substring(2);
			}
			newDate += dateString;
			dateString = newDate;
		}
		dateString = "&nbsp;" + dateString + "&nbsp;";
		
		
		if(!timestamp.isAnnoDomini()){
			dateString += "<br/>&emsp;&emsp;b.c.";
		}

		
		TransparentPanel lineContact = new TransparentPanel();
		lineContact.setOpaque(false);
		lineContact.setLayout(new GridLayout(1, 0, 0, 0));
		contactArrow = new SimpleLabel("");
		contactArrow.setHorizontalTextPosition(SimpleLabel.CENTER);
		contactArrow.setVerticalTextPosition(SimpleLabel.CENTER);
		contactDate = new JLabel("<html>" + dateString + "</html>");
		contactDate.setHorizontalTextPosition(JLabel.CENTER);
		contactDate.setVerticalTextPosition(JLabel.CENTER);
		if(leftPosition) {
			//When Item is on left Side, contact must on right side
			add(lineContact, BorderLayout.EAST);
			lineContact.add(contactArrow);
			lineContact.add(contactDate);
			contactArrow.setText(FRONT_TAG + "&#8680;" + BACK_TAG);
		} else {			
			add(lineContact, BorderLayout.WEST);
			lineContact.add(contactDate);
			lineContact.add(contactArrow);
			contactArrow.setText(FRONT_TAG + "&#8678;" + BACK_TAG);
		}
		
		TransparentPanel my_center = new TransparentPanel();
		add(my_center);
		my_center.setLayout(new BorderLayout(0, 0));
		
		my_body = new TransparentPanel();
		my_center.add(my_body, BorderLayout.CENTER);
		my_body.setLayout(new GridLayout(0, 1, 5, 5));
		
		SimpleLabel lblIsSpecific = new SimpleLabel("");
		my_body.add(lblIsSpecific);
		if(timestamp.isSpecificDate()) {
			lblIsSpecific.setText("Specific Date");
		} else {
			lblIsSpecific.setText("Unspecific Date");
		}
		
		panelOverBody = new TransparentPanel();
		my_center.add(panelOverBody, BorderLayout.NORTH);
		panelOverBody.setLayout(new GridLayout(0, 1, 0, 0));
//		panelOverBody.setLayout(new BoxLayout(panel_tableOfContent, BoxLayout.PAGE_AXIS));
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		panelOverBody.add(rigidArea);
		
//		System.out.println(this.getPreferredSize().height); -> 47
		topRightGab = new JPanel();		
		changeTheme();
	}
	
	public TimelineItem(boolean leftPosition) {
		setLayout(new BorderLayout(0, 0));
		
		topRightGab = new JPanel();
		if(leftPosition) {
			add(topRightGab, BorderLayout.EAST);
		} else {			
			add(topRightGab, BorderLayout.WEST);
		}
		int half = 47 / 2;
		Component topGab = Box.createRigidArea(new Dimension(58, half));
		topRightGab.add(topGab);
		
		my_body = new TransparentPanel();
		contactArrow = new SimpleLabel("");
		contactDate = new JLabel("");
		
		changeTheme();
	}
	
	public TimelineItem(boolean leftPosition, int gabHeight) {
		setLayout(new BorderLayout(0, 0));
		
		topRightGab = new JPanel();
		if(leftPosition) {
			add(topRightGab, BorderLayout.EAST);
		} else {			
			add(topRightGab, BorderLayout.WEST);
		}
		Component topGab = Box.createRigidArea(new Dimension(58, gabHeight));
		topRightGab.add(topGab);
		
		my_body = new TransparentPanel();
		contactArrow = new SimpleLabel("");
		contactDate = new JLabel("");
		
		changeTheme();
	}

	private void changeTheme() {
		if(ThemeList.currentTheme != null) {
			topRightGab.setBackground(ThemeList.currentTheme.menuBackColor);
			contactDate.setForeground(ThemeList.currentTheme.menuFontColor);
			contactDate.setBackground(ThemeList.currentTheme.menuBackColor);
			contactDate.setOpaque(true);
//			contactDate.setForeground(ThemeList.currentTheme.darkForegroundColor);
			my_body.setBorder(BorderFactory.createLineBorder(ThemeList.currentTheme.menuBackColor));
			contactArrow.setForeground(ThemeList.currentTheme.menuBackColor);
			revalidate();
			repaint();
		} else {
			my_body.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}
	}
	
	public void addToBody(Component bodyComponent) {
		my_body.add(bodyComponent);
	}
	
	public void setSection(TransparentPanel sectionHeader) {
		panelOverBody.add(sectionHeader);
		revalidate();
		repaint();
	}

}
