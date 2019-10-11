package global;

public class Tag {
	
	private ObjectID referredTo;
	private String refClassType;
	
	public Tag(ObjectID newReference, String newClassType) {
		referredTo = newReference;
		refClassType = newClassType;
	}
	
	public ObjectID getRefID() {
		return referredTo;
	}
	
	public String getType() {
		return refClassType;
	}
	

}
