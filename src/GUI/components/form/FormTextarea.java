package GUI.components.form;

import GUI.components.SimpleLabel;
import GUI.components.SimpleTextarea;
import GUI.components.form.FormInput;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FormTextarea extends FormInput {
	private static final long serialVersionUID = 1L;
	
	private final SimpleLabel lblNameLabel;
	
	private final SimpleTextarea textArea;
	private String originalText;

	public FormTextarea(String name, String text) {
		setLayout(new BorderLayout(2, 2));
		
		lblNameLabel = new SimpleLabel(name);
		add(lblNameLabel, BorderLayout.NORTH);
		
		textArea = new SimpleTextarea(text);
		add(textArea, BorderLayout.CENTER);

		originalText = text;
	
		textArea.addKeyListener(new KeyAdapter() {
	        @Override
            public void keyPressed(KeyEvent e) {
                if ((e.getKeyCode() == KeyEvent.VK_S) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
            		parentForm.callAction();
                }
            }
	    });
		
		textArea.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				checkChanges();
				parentForm.canBeSaved();
			}
			public void removeUpdate(DocumentEvent e) {
				checkChanges();
				parentForm.canBeSaved();
			}
			public void insertUpdate(DocumentEvent e) {
				checkChanges();
				parentForm.canBeSaved();
			}
		});
	}	
	
	public String getText(){
		return textArea.getText();
	}
	
	protected void checkChanges(){
		boolean hasChanges = !originalText.equals(textArea.getText());
		lblNameLabel.setWarning(hasChanges);
		textArea.setWarning(hasChanges);
	}
	
	@Override
	protected void actionWasCalled(){
		textArea.setWarning(false);
		lblNameLabel.setWarning(false);
		originalText = textArea.getText();
	}

	@Override
	public boolean canBeSaved() {
		//TODO: add maybe later a check for isRequired or isNotRequired inside the form
		return true;
	}
}
