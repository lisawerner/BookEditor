package global;

public class SerializedObject {
	
	protected ObjectID my_uID;
	
	public SerializedObject() {
		my_uID = new ObjectID(this.getClass().getName());
	}
	
	public void setID(){ //TODO delete this function ???
		if(my_uID == null){
			my_uID = new ObjectID(this.getClass().getName());
		}
	}
	
	public ObjectID getID() {
		return my_uID;
	}

}
