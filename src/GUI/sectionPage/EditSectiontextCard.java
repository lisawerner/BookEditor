package GUI.sectionPage;

import GUI_components.SimpleTextarea;
import GUI_components.TransparentPanel;
import book.Section;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EditSectiontextCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	private Section my_section;
	
//	private String frontTag = "<html>";
//	private String backTag = "</html>";
	
	private SimpleTextarea textArea;
	private JLabel lblSaveHint;
	
	public EditSectiontextCard(Section openedSection) {
		my_section = openedSection;
		setLayout(new BorderLayout(10, 10));
		
		TransparentPanel panel_changeFontButtons = new TransparentPanel();
		add(panel_changeFontButtons, BorderLayout.NORTH);
		panel_changeFontButtons.setLayout(new GridLayout(1, 0, 5, 5));
		
		//TODO: Try again to add bold/italic and other stuff to text.... :/
		JButton btnBold = new JButton("bold");
		panel_changeFontButtons.add(btnBold);
		btnBold.setEnabled(false);
//		btnBold.addActionListener(e -> makeTextBold());
		
		JButton btnItalic = new JButton("italic");
		panel_changeFontButtons.add(btnItalic);
		btnItalic.setEnabled(false);
		
		textArea = new SimpleTextarea(); //TextArea and TextField can not use html!
//		textArea = new JTextPane();
//		textArea.setContentType("text/html");
		if(my_section != null) {
			textArea.setText(my_section.getText());
//			if(my_section.getText().contains(frontTag)) {				
//				textArea.setText(my_section.getText());
//			} else {
//				textArea.setText(frontTag + my_section.getText() + backTag);
//			}
		}
		add(textArea, BorderLayout.CENTER);
		textArea.addKeyListener(new KeyAdapter() {
	        @Override
            public void keyPressed(KeyEvent e) {
                if ((e.getKeyCode() == KeyEvent.VK_S) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
                	save();
                }
            }

	    });
		textArea.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				setSavestatus();
			}
			public void removeUpdate(DocumentEvent e) {
				setSavestatus();
			}
			public void insertUpdate(DocumentEvent e) {
				setSavestatus();
			}
		});
		
		JButton btnSave = new JButton("Save");
		add(btnSave, BorderLayout.EAST);
		btnSave.addActionListener(e -> save());
		
		lblSaveHint = new JLabel("<html>You can also save by pushing 'STRG+S' when the curser is inside the textarea!<br>Last saved:</html>");
		add(lblSaveHint, BorderLayout.SOUTH);

	}

//	private void makeTextBold() {
//		String text = textArea.getSelectedText();
//		textArea.replaceSelection("<b>" + text + "</b>");
//		textArea.setContentType("text/html");
//		textArea.revalidate();
//		textArea.repaint();
//	}

	private void save() {
		my_section.setText(textArea.getText());
		setSavestatus();
	}
	
	private void setSavestatus() {
		if(my_section.getText().equals(textArea.getText())) {			
			lblSaveHint.setText("<html>You can also save by pushing 'STRG+S' when the curser is inside the textarea!<br>Already saved</html>");
		} else {
			lblSaveHint.setText("<html>You can also save by pushing 'STRG+S' when the curser is inside the textarea!<br><font color=\"red\">NOT saved at the moment! Press STRG+S</font></html>");
		}
	}
}
