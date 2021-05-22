package GUI_components;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

public class TextPreview extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	private final JPanel panel_viewport;
	private final SimpleTextarea lblText;
	
	public TextPreview(String text, boolean isReview, boolean setHeight) {
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		scrollPane.setViewportBorder(BorderFactory.createEmptyBorder());
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		add(scrollPane);
		
		panel_viewport = new JPanel();
		panel_viewport.setLayout(new GridLayout(0, 1, 0, 0));
		
		JViewport viewport = new JViewport();
		viewport.setOpaque(false);
		scrollPane.setViewportView(viewport);
		viewport.setView(panel_viewport);
		
		lblText = new SimpleTextarea(text);
		panel_viewport.add(lblText);
		lblText.setEditable(false);	
		
		if(setHeight) {
			Font font = lblText.getFont();
			int height = font.getSize() + 4; //Why +4 ??? TODO: Maybe there is something wrong...
			
			Dimension dim = scrollPane.getSize();
			dim.setSize(dim.getWidth(), height*3);
			scrollPane.setSize(dim);
			scrollPane.setPreferredSize(dim);
		}
		if(isReview) {
			//Scroll to Bottom
			scrollPane.getViewport().setViewPosition(new Point(0, lblText.getDocument().getLength()));
		}

		changeTheme();
	}
	
	private void changeTheme() {
		if(ThemeList.currentTheme != null) {
			if(ThemeList.currentTheme.darkTheme) {
				panel_viewport.setBackground(ThemeList.currentTheme.darkBackgroundColor);
				lblText.setBackground(ThemeList.currentTheme.darkBackgroundColor);
				lblText.setForeground(ThemeList.currentTheme.darkForegroundColor);
			} else {				
				panel_viewport.setBackground(ThemeList.currentTheme.lightBackgroundColor);
				lblText.setBackground(ThemeList.currentTheme.lightBackgroundColor);
				lblText.setForeground(ThemeList.currentTheme.lightForegroundColor);
			}
			revalidate();
			repaint();
		}
	}
	
}
