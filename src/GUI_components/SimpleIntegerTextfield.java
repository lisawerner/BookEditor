package GUI_components;

public class SimpleIntegerTextfield extends SimpleTextfield{
	private static final long serialVersionUID = 1L;
	
	public int getInteger() {
		if("".equals(this.getText())) {
			return 0;
		}
		return Integer.parseInt(this.getText());
	}
}
