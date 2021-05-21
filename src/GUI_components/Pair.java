package GUI_components;

public class Pair {
	

	private final String label;
    private final int value;

    public Pair(String label, int value) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return this.value;
    }

    public String getLabel() {
        return this.label;
    }
    
    @Override
    public String toString() {
        return label;
    }

}
