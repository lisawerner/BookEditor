package GUI.components;

import GUI.theme.ThemeList;
import global.UserSettings;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class TextPreview extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	public final static String TYPE_PREVIEW_BEFORE = "previous_text";
	public final static String TYPE_PREVIEW_AFTER = "following_text";

	private final JScrollPane scrollPane;
	private final JPanel panel_viewport;
	private final SimpleTextarea lblText;
	
	public TextPreview(String text, String type) {
		this.validateInput(type);

		setLayout(new GridLayout(0, 1, 0, 0));
		
		scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		scrollPane.setViewportBorder(BorderFactory.createEmptyBorder());
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane);
		
		panel_viewport = new JPanel();
		panel_viewport.setLayout(new GridLayout(0, 1, 0, 0));
		
		JViewport viewport = new JViewport();
		viewport.setOpaque(false);
		scrollPane.setViewportView(viewport);
		viewport.setView(panel_viewport);
		
		lblText = new SimpleTextarea(text);
		lblText.setFont(this.getFont().deriveFont((float) UserSettings.getInstance().getTextareaFontSize()));
		panel_viewport.add(lblText);
		lblText.setEditable(false);	

		this.setPreviewAreaHeight();

		if(type.equals(TYPE_PREVIEW_BEFORE)) {
			//Scroll to bottom of the preview text
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

	private void validateInput(String type) {
		String[] TYPES = new String[]{TYPE_PREVIEW_BEFORE, TYPE_PREVIEW_AFTER};
		if(!Arrays.asList(TYPES).contains(type)) {
			throw new IllegalArgumentException();
		}
	}

	private void setPreviewAreaHeight() {
		Font font = lblText.getFont();
		int height = font.getSize();

		Dimension dim = scrollPane.getSize();
		dim.setSize(dim.getWidth(), height*4); //TODO: Why 4 when i want to show 3 lines?
		scrollPane.setSize(dim);
		scrollPane.setPreferredSize(dim);
	}

}
