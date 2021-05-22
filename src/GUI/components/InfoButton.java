package GUI.components;

import GUI.theme.ThemeList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InfoButton extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private final JLabel my_label;
	
	private boolean isMenuInfoHint;
	
	public InfoButton(String hint) {
		setOpaque(false);
		setPreferredSize(new Dimension(16, 16));
		my_label = new JLabel("i");
		add(my_label, BorderLayout.NORTH);
		my_label.setToolTipText(hint);
		my_label.setHorizontalTextPosition(JLabel.CENTER);
		my_label.setVerticalTextPosition(JLabel.CENTER);
		setToolTipText(hint);
		isMenuInfoHint = false;
		
		my_label.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	JOptionPane.showMessageDialog(null, hint);
		    	//TODO: Make own JOptionPane in correct Theme-Design
		    }  
		});
		addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	JOptionPane.showMessageDialog(null, hint);
		    	//TODO: Make own JOptionPane in correct Theme-Design
		    }  
		});
		
		changeTheme();
	}
	
	private void changeTheme() {
		if(ThemeList.currentTheme != null) {
			if(isMenuInfoHint) {
				setForeground(Color.WHITE);
				my_label.setForeground(Color.WHITE);
			} else {				
				//TODO: This color is very ugly... It is ugly in all themes and with light and dark mode o.O
				setForeground(ThemeList.currentTheme.cardTitleFont);
				my_label.setForeground(ThemeList.currentTheme.cardTitleFont);
			}
			revalidate();
			repaint();
		} else {
			setForeground(Color.GRAY);
//			System.out.println("Change Theme in Component to: Default");
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    //Adding  super.paintComponent....  
	    super.paintComponent(g);
	    g.drawOval(0, 5, 15, 15);
    }
	
	public void setText(String newToolTipText) {
		my_label.setToolTipText(newToolTipText);
	}

	public void setMenuInfo() {
		isMenuInfoHint = true;
		changeTheme();
	}
}
