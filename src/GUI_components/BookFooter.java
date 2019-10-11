package GUI_components;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;

public class BookFooter extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JButton btnAbout;
	private JLabel lblCopyRight;
	
	public BookFooter() {
		setLayout(new BorderLayout(0, 0));
		
		TransparentPanel panel = new TransparentPanel();
		add(panel);
		btnAbout = new JButton("<html>&ensp; About &ensp;</html>");
		panel.add(btnAbout);
		//TODO: About-Text fertig schreiben!
		String aboutText = "<html><h1>About BookEditor</h1>"
				+ "<p>Copyright: All Rights reserved by Lisa Werner</p>"
				+ "<p></p>"
				+ "<p>More on TODO: Website...</p></html>";
		btnAbout.addActionListener(e -> JOptionPane.showMessageDialog(null, aboutText));
		
		//TODO: Copyright richtig machen. Mit Jahrszahl???
		lblCopyRight = new JLabel("<html><font size=\"5\">&#9400;</font> Lisa Werner &emsp;</html>");
		add(lblCopyRight, BorderLayout.EAST);
		
		//TODO: Datenschutz? Kontakt? Support? Was noch? Hilfe (dort z.B. Restart Tutorial? Oder das in denS ettings machen??? oder an beiden Stellen?)!
		
		changeTheme();
	}
	
	public void changeTheme() {
		if(ThemeList.currentTheme != null) {
			this.setBackground(ThemeList.currentTheme.headerBackColor);
			btnAbout.setForeground(ThemeList.currentTheme.headerFontColor);
			btnAbout.setBackground(ThemeList.currentTheme.headerBackColor);
			btnAbout.setBorder(BorderFactory.createLineBorder(ThemeList.currentTheme.headerFontColor));
			lblCopyRight.setForeground(ThemeList.currentTheme.headerFontColor);
			revalidate();
			repaint();
		} else {
//			System.out.println("Change Theme in Component to: Default");
		}
	}
}
