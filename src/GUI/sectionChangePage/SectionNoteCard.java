package GUI.sectionChangePage;

import book.Section;
import GUI.components.SimpleLabel;
import GUI.components.SimpleTextarea;
import GUI.components.TransparentPanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.BorderLayout;

public class SectionNoteCard extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private Section my_section;
	
	private SimpleTextarea textArea;
	private SimpleLabel lblSaveHint;
	
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
				setSavestatus();
			}
			public void removeUpdate(DocumentEvent e) {
				setSavestatus();
			}
			public void insertUpdate(DocumentEvent e) {
				setSavestatus();
			}
		});
		
	}
	
	private void save() {
		my_section.setNotes(textArea.getText());
		setSavestatus();
	}
	
	private void setSavestatus() {
		if(my_section.getNotes().equals(textArea.getText())) {			
			lblSaveHint.setText("<html>You can also save by pushing 'STRG+S' when the curser is inside the textarea!<br>Already saved</html>");
		} else {
			lblSaveHint.setText("<html>You can also save by pushing 'STRG+S' when the curser is inside the textarea!<br><font color=\"red\">NOT saved at the moment! Press STRG+S</font></html>");
		}
	}

}
