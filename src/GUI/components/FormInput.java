package GUI.components;

public abstract class FormInput extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	protected SimpleForm parentForm;

	public FormInput() {
		
	}

	protected void setParentForm(SimpleForm newParentForm){
		parentForm = newParentForm;
	}
	
	protected boolean checkSavebility(){
		return true;
	}
	
	protected void actionWasCalled(){
		
	}
	
}
