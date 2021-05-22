package GUI.components;

import GUI.HelpFrame;
import GUI.theme.ThemeList;

import javax.swing.*;
import java.awt.*;

public class FrameFooter extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private final JButton btnAbout;
	private final JLabel lblCopyRight;
	private final JButton btnHelp;
	
	public FrameFooter() {
		setLayout(new BorderLayout(0, 0));
		
		TransparentPanel panel = new TransparentPanel();
		add(panel);
		btnAbout = new JButton("<html>&ensp; About &ensp;</html>");
		panel.add(btnAbout);
		//TODO: Finish writing the About-Text!
		String aboutText = "<html><h1>About BookEditor</h1>"
				+ "<p>Copyright: All Rights reserved by Lisa Werner</p>"
				+ "<p></p>"
				+ "<p>More on TODO: Website...</p></html>";
		btnAbout.addActionListener(e -> JOptionPane.showMessageDialog(null, aboutText));
		
		btnHelp = new JButton("<html>&nbsp; &ensp; Help &ensp; &nbsp;</html>");
		btnHelp.addActionListener(e -> {
			HelpFrame frame = new HelpFrame();
			frame.setVisible(true);
		});
		panel.add(btnHelp);
		
		//TODO: Write a correct Copyright (e.g. with year?)
		lblCopyRight = new JLabel("<html><font size=\"5\">&#9400;</font> Lisa Werner &emsp;</html>");
		add(lblCopyRight, BorderLayout.EAST);
		
		//TODO: Datenschutz? Contact? Support? Terms of Use? What else to add?
		
		changeTheme();
	}
	
	public void changeTheme() {
		if(ThemeList.currentTheme != null) {
			this.setBackground(ThemeList.currentTheme.headerBackColor);
			btnAbout.setForeground(ThemeList.currentTheme.headerFontColor);
			btnAbout.setBackground(ThemeList.currentTheme.headerBackColor);
			btnAbout.setBorder(BorderFactory.createLineBorder(ThemeList.currentTheme.headerFontColor));
			btnHelp.setForeground(ThemeList.currentTheme.headerFontColor);
			btnHelp.setBackground(ThemeList.currentTheme.headerBackColor);
			btnHelp.setBorder(BorderFactory.createLineBorder(ThemeList.currentTheme.headerFontColor));
			lblCopyRight.setForeground(ThemeList.currentTheme.headerFontColor);
			revalidate();
			repaint();
		} else {
			this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}
	}
}
