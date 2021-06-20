package GUI.pages.content.changeSection;

import GUI.components.SimpleLabel;
import GUI.components.TransparentPanel;
import GUI.components.form.FormButton;
import GUI.components.form.FormTextarea;
import GUI.components.form.SimpleForm;
import book.content.Section;

import java.awt.*;

public class Card_EditSectionText extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	private final Section my_section;
	
//	private String frontTag = "<html>";
//	private String backTag = "</html>";
	
	private final FormTextarea textArea;
	private final SimpleLabel lblCounts;
	
	public Card_EditSectionText(Section openedSection) {
		my_section = openedSection;
		setLayout(new BorderLayout(10, 10));

		TransparentPanel panel_footer = new TransparentPanel();
		panel_footer.setLayout(new GridLayout(0, 1, 5, 5));
		add(panel_footer, BorderLayout.SOUTH);

		FormButton btnSave = new FormButton("Save", e -> save());
		panel_footer.add(btnSave);
		SimpleForm saveForm = new SimpleForm(this::save, btnSave);

		textArea = new FormTextarea("", my_section != null ? my_section.getText() : "");
		add(textArea, BorderLayout.CENTER);
		saveForm.addInput(textArea);

	//	TextArea and TextField can not use html!
	//	textArea.setContentType("text/html");
	//	textArea.setText(frontTag + my_section.getText() + backTag);

		add(textArea, BorderLayout.CENTER);
		
		lblCounts = new SimpleLabel("Words: ...; Char: ...");
		updateCounts();
		panel_footer.add(lblCounts);
		
		TransparentPanel panel_header = new TransparentPanel();
		add(panel_header, BorderLayout.NORTH);
		panel_header.setLayout(new GridLayout(0, 1, 5, 5));
		
		TransparentPanel panel_changeFontButtons = new TransparentPanel();
		panel_header.add(panel_changeFontButtons);
		panel_changeFontButtons.setLayout(new GridLayout(1, 0, 5, 5));
		
		//TODO: Try again to add bold/italic and other stuff to text.... :/
//		JButton btnBold = new JButton("bold");
//		panel_changeFontButtons.add(btnBold);
//		btnBold.setEnabled(false);
//		//btnBold.addActionListener(e -> makeTextBold());
//
//		JButton btnItalic = new JButton("italic");
//		panel_changeFontButtons.add(btnItalic);
//		btnItalic.setEnabled(false);

	}

//	private void makeTextBold() {
//		String text = textArea.getSelectedText();
//		String originalText = textArea.getText();
//		
//		int start = textArea.getSelectionStart();
//		int end = textArea.getSelectionEnd();
//		originalText = originalText.substring(0, start-1) + "<b>" + text + "</b>" + originalText.substring(end+1);
//		//textArea.setContentType("text/html");
//		textArea.setText(originalText);
//		
//		textArea.revalidate();
//		textArea.repaint();
//	}

	public void save() {
		my_section.setText(textArea.getText());
		updateCounts();
	}
	
	private void updateCounts() {
		lblCounts.setText("Words: " + textArea.getText().split("\\s+").length + "; Char: " + textArea.getText().length());
	}
}
