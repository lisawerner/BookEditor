package GUI.components;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class FormTextfield extends FormInput {
	private static final long serialVersionUID = 1L;

	private boolean isNeededForSaving;
	
	private SimpleLabel lblTextfieldlabel;
	
	private SimpleTextfield textField;
	private String originalText;
	
	/**
	 * Creates a text field with an own label for naming the text field
	 * 
	 * @param textfieldLabel
	 * @param text set text if the text field already has any input
	 * @param setIsNeededForSaving set true if text field is not to be allowed to be empty
	 */
	public FormTextfield(String textfieldLabel, String text, boolean setIsNeededForSaving) {
		setLayout(new BorderLayout(5, 5));
		
		isNeededForSaving = setIsNeededForSaving;
		
		lblTextfieldlabel = new SimpleLabel(textfieldLabel);
		add(lblTextfieldlabel, BorderLayout.WEST);
				
		textField = new SimpleTextfield(text);
		add(textField, BorderLayout.CENTER);
		originalText = text;
		
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
				parentForm.checkAllSavebilities();
			}
			public void removeUpdate(DocumentEvent e) {
				checkChanges();
				parentForm.checkAllSavebilities();
			}
			public void insertUpdate(DocumentEvent e) {
				checkChanges();
				parentForm.checkAllSavebilities();
			}
		});
	}
	
	@Override
	protected boolean checkSavebility(){
		return (!isNeededForSaving) 
				|| (isNeededForSaving && !getText().isEmpty());
	}	
	
	public String getText(){
		return textField.getText();
	}
	
	protected void checkChanges(){
		boolean hasChanges = !originalText.equals(textField.getText());
		lblTextfieldlabel.setWarning(hasChanges);
		textField.setWarning(hasChanges);
	}
	
	@Override
	protected void actionWasCalled(){
		textField.setWarning(false);
		lblTextfieldlabel.setWarning(false);
		originalText = textField.getText();
	}
}
