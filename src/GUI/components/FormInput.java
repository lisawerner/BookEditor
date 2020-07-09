package GUI.components;

public abstract class FormInput extends TransparentPanel {
	private static final long serialVersionUID = 1L;

	protected SimpleFormular parentForm;

	public FormInput() {
		
	}

	protected void setParentForm(SimpleFormular newParentFormular){
		parentForm = newParentFormular;
	}
	
	protected boolean checkSavebility(){
		return true;
	}
	
	protected void actionWasCalled(){
		
	}
	
}
