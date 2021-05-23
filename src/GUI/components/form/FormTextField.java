package GUI.components.form;

import GUI.components.SimpleLabel;
import GUI.components.SimpleTextField;
import GUI.components.form.FormInput;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class FormTextField extends FormInput {
	private static final long serialVersionUID = 1L;

	private final boolean isRequiredForSaving;
	
	private final SimpleLabel lblTextFieldLabel;
	
	private final SimpleTextField textField;
	private String originalText;
	
	/**
	 * Creates a text field with an own label for naming the text field
	 * 
	 * @param textFieldLabel label to name the text field
	 * @param textFieldContent set text if the text field already has any input
	 * @param setIsRequiredForSaving set true if text field is not to be allowed to be empty
	 */
	public FormTextField(String textFieldLabel, String textFieldContent, boolean setIsRequiredForSaving) {
		setLayout(new BorderLayout(5, 5));
		
		isRequiredForSaving = setIsRequiredForSaving;
		
		lblTextFieldLabel = new SimpleLabel(textFieldLabel);
		add(lblTextFieldLabel, BorderLayout.WEST);
				
		textField = new SimpleTextField(textFieldContent);
		add(textField, BorderLayout.CENTER);
		originalText = textFieldContent;
		
		textField.addKeyListener(new KeyAdapter() {
	        @Override
            public void keyPressed(KeyEvent e) {
                if ((e.getKeyCode() == KeyEvent.VK_S) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
            		parentForm.callAction();
                }
            }

	    });
		
		textField.getDocument().addDocumentListener(new DocumentListener() {
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
	
	@Override
	protected boolean canBeSaved(){
		return !isRequiredForSaving || !getText().isEmpty();
	}
	
	public String getText(){
		return textField.getText();
	}
	
	protected void checkChanges(){
		boolean hasChanges = !originalText.equals(textField.getText());
		lblTextFieldLabel.setWarning(hasChanges);
//		textField.setWarning(hasChanges); //TODO: Why is that a problem for the TextField but not for the TextArea?
	}
	
	@Override
	protected void actionWasCalled(){
		textField.setWarning(false);
		lblTextFieldLabel.setWarning(false);
		originalText = textField.getText();
	}
}
