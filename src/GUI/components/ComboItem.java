package GUI.components;

import global.ObjectID;

public class ComboItem {

	private final String label;
    private final ObjectID value;

    public ComboItem(String label, ObjectID value) {
        this.value = value;
        this.label = label;
    }

    public ObjectID getValue() {
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
