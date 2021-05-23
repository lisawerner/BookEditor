package GUI.components.form;

import GUI.components.TransparentPanel;

public abstract class FormInput extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	protected SimpleForm parentForm;

	public FormInput() {}

	protected void setParentForm(SimpleForm newParentForm){
		parentForm = newParentForm;
	}
	
	protected abstract boolean canBeSaved();
	
	protected abstract void actionWasCalled();

}
