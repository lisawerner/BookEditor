package book.content;

public class DevelopmentStatus {
	
	public static String getDevStatus(int id) {
		switch(id) {
		case 0:
			return "Empty";
		case 1:
			return "Only Notes";
		case 2:
			return "TODO: rework Text";
		case 3:
			return "TODO: correct Text";
		case 4:
			return "Finish";
		case -1:
			// As default
		default:
			return "unknown";
		}
	}
	
	public static String getDevStatDescription(int id) {
		switch(id) {
		case 0:
			return "This Section is only a placeholder and there are no information inside at the moment.";
		case 1:
			return "This Section is planed and notes are written down, but no text exist at the moment.";
		case 2:
			return "First Text-Version(s) are written, but content and structure isn't finished yet.";
		case 3:
			return "Text is finished, but should be proofread.";
		case 4:
			return "Text is finished structured and proofread and can be printed as it is.";
		case -1:
			// As default
		default:
			return "Development Status is not set yet.";
		}
	}

}