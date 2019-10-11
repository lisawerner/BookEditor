package global;

public class UserSettings {
	
	private static UserSettings my_instance;
	private final static String my_filename = "usersettings.json";
	
	private Tutorial user_tutorial;
	
	private UserSettings() {
		user_tutorial = new Tutorial();
	}
	
	public static UserSettings getInstance() {
		if(my_instance == null) {
			my_instance = (UserSettings) FileManager.loadJSONFile(my_filename, UserSettings.class);
		}
		if(my_instance == null) {
			my_instance = new UserSettings();
		}
		return my_instance;
	}
	
	public Tutorial getTutorial() {
		return user_tutorial;
	}

	public void save() {
		FileManager.saveJSONFile(my_filename, this);
	}

}
