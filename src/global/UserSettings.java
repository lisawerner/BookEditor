package global;

public class UserSettings {
	
	private static UserSettings my_instance;
	public final static String USER_SETTINGS_FILE_NAME = "user_settings.json";
	
	private Tutorial user_tutorial;
	private boolean bigScreenSize;
	private int textareaFontSize;
	private String lastOpenedBookFile;
	
	private UserSettings() {
		user_tutorial = new Tutorial();
		bigScreenSize = false;
		textareaFontSize = 12;
		lastOpenedBookFile = "";
	}
	
	public static UserSettings getInstance() {
		if(my_instance == null) {
			my_instance = (UserSettings) FileManager.loadJSONFile(USER_SETTINGS_FILE_NAME, UserSettings.class);
		}
		if(my_instance == null) {
			my_instance = new UserSettings();
		}
		return my_instance;
	}
	
	public void save() {
		FileManager.saveJSONFile(USER_SETTINGS_FILE_NAME, this);
	}
	
	public Tutorial getTutorial() {
		return user_tutorial;
	}
	
	public void restartTutorial() {
		user_tutorial = new Tutorial();
		save();
	}
	
	public void setDisplaySettings(boolean setDisplayToScreenSizeSettings) {
		bigScreenSize = setDisplayToScreenSizeSettings;
		save();
	}

	public boolean getDisplaySettings() {
		return bigScreenSize;
	}
	
	public int getTextareaFontSize() {
		return textareaFontSize;
	}
	
	public void setTextAreaSize(int newTextareaFontSize) {
		textareaFontSize = newTextareaFontSize;
		save();
	}
	
	public String getLastOpenedBookFile() {
		return lastOpenedBookFile;
	}

	public void setLastOpenedBookFile(String selectedBook) {
		lastOpenedBookFile = selectedBook;
		save();
	}
}
