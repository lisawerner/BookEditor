package global;

public class BookObject {
	
	protected ObjectID my_uID;
	
	public BookObject() {
		my_uID = new ObjectID(this.getClass().getName());
	}
	
	public ObjectID getID() {
		return my_uID;
	}

}
