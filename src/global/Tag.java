package global;

public class Tag {
	
	private final ObjectID referredTo;
	private final String refClassType;
	
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
