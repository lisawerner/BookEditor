package global;
import java.util.UUID;

public class ObjectID {
	
	private String my_id;
	
	public ObjectID(String objectType) {
		my_id = this.getNewID(objectType);
	}
	
	/**
	 * Creates an unique ID by adding the current Time in Miliseconds to the ID-String
	 * @param objectType = You should give ClassType with "this.getClass().getName()"
	 * @return newID
	 */
	private String getNewID(String objectType) {
	    String ts = String.valueOf(System.currentTimeMillis());
//	    System.out.println("Time: " + ts);
	    String rand = UUID.randomUUID().toString();
//	    System.out.println("UUID: " + rand);
	    String newID = ts + objectType + rand;
//	    System.out.println("New ID: " + newID);
	    return newID;
	}
	
	public String getIDtoString() {
		return my_id;
	}

}