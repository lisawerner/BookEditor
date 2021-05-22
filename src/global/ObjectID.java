package global;
import java.util.UUID;

public class ObjectID {
	
	private final String my_id;
	
	public ObjectID(String objectType) {
		my_id = this.getNewID(objectType);
	}
	
	/**
	 * Creates an unique ID by adding the current Time in Milliseconds to the ID-String
	 * @param objectType = You should give ClassType with "this.getClass().getName()"
	 * @return newID
	 */
	private String getNewID(String objectType) {
	    String ts = String.valueOf(System.currentTimeMillis());
//	    System.out.println("Time: " + ts);
	    String rand = UUID.randomUUID().toString();
//	    System.out.println("UUID: " + rand);
	    return ts + objectType + rand;
	}
	
	public boolean equals(ObjectID otherID) {
		return my_id.equals(otherID.getIDtoString());
	}
	
	public String getIDtoString() {
		return my_id;
	}

}