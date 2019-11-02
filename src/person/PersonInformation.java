package person;

import book.Book;

public class PersonInformation {

	private String my_name;
	private String my_nickname;
	
	private String my_age;
	private boolean age_startAtBook;
	private boolean age_startAtFirstAppearance;
	
	private boolean isSuperMainCharapter;
	private boolean isMainCharapter;
	
	private String my_notes;
	
	protected PersonInformation(String newName, String newNickname, String newAge, boolean ageBookStart, boolean ageFirstAppearance, 
			boolean newIsSuperMainCharapter, boolean newIsMainCharapter,
			String newNotes) {
		my_name = newName;
		my_nickname = newNickname;
		
		my_age = newAge;
		age_startAtBook = ageBookStart;
		age_startAtFirstAppearance = ageFirstAppearance;
		
		isSuperMainCharapter = newIsSuperMainCharapter;
		isMainCharapter = newIsMainCharapter;
		
		my_notes = newNotes;
	}
	
	public void editInformation(String newName, String newNickname, String newAge, boolean ageBookStart, boolean ageFirstAppearance, 
			boolean newIsSuperMainCharapter, boolean newIsMainCharapter,
			String newNotes) {
		my_name = newName;
		my_nickname = newNickname;
		
		my_age = newAge;
		age_startAtBook = ageBookStart;
		age_startAtFirstAppearance = ageFirstAppearance;
		
		isSuperMainCharapter = newIsSuperMainCharapter;
		isMainCharapter = newIsMainCharapter;
		
		my_notes = newNotes;
		Book.getInstance().getSociety().sortPersons();
		Book.getInstance().save();
	}
	
	public String getName() {
		return my_name;
	}
	
	public String getNickname() {
		if(my_nickname == null) { return my_name;}
		if("".equals(my_nickname)) { return my_name;}
		return my_nickname;
	}
	
	public String getAge() {
		return my_age;
	}
	
	public boolean getAgeBookStart() {
		return age_startAtBook;
	}
	
	public boolean getAgeFirstAppear() {
		return age_startAtFirstAppearance;
	}
	
	public String getNotes() {
		return my_notes;
	}
	
	public boolean isSuperMainChar() {
		return isSuperMainCharapter;
	}

	public boolean isFrequentlyChar() {
		return !isSuperMainCharapter && isMainCharapter;
	}

	public boolean isSomebody() {
		return !isSuperMainCharapter && !isMainCharapter;
	}	
}
