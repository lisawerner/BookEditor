package GUI.sectionChangePage;

import GUI.components.SimpleLabel;
import GUI.components.SimpleTextarea;
import GUI.components.TransparentPanel;
import book.Section;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SectionNoteCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private final Section my_section;
	
	private final SimpleTextarea textArea;
	private final SimpleLabel lblSaveHint;
	
	public SectionNoteCard(Section section) {
		my_section = section;
		setLayout(new BorderLayout(5, 5));
		
		textArea = new SimpleTextarea();
		add(textArea, BorderLayout.CENTER);
		textArea.setText(my_section.getNotes());
		
		lblSaveHint = new SimpleLabel(" ");
		add(lblSaveHint, BorderLayout.SOUTH);
		
		JButton btnSave = new JButton("Save");
		add(btnSave, BorderLayout.EAST);
		btnSave.addActionListener(e -> save());
		
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
				setSaveStatus();
			}
			public void removeUpdate(DocumentEvent e) {
				setSaveStatus();
			}
			public void insertUpdate(DocumentEvent e) {
				setSaveStatus();
			}
		});
		
	}
	
	private void save() {
		my_section.setNotes(textArea.getText());
		setSaveStatus();
	}
	
	private void setSaveStatus() {
		if(my_section.getNotes().equals(textArea.getText())) {			
			lblSaveHint.setText("<html>You can also save by pushing 'STRG+S' when the curser is inside the textarea!<br>Already saved</html>");
		} else {
			lblSaveHint.setText("<html>You can also save by pushing 'STRG+S' when the curser is inside the textarea!<br><font color=\"red\">NOT saved at the moment! Press STRG+S</font></html>");
		}
	}

}
