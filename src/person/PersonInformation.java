package person;

import book.Book;
import global.ObjectID;

public class PersonInformation {

	private String my_name;
	private String my_nickname;
	
	private String my_age;
	private boolean age_startAtBook;
	private boolean age_startAtFirstAppearance;
	private boolean death_isDeathBeforeBookStart;
	private String my_deathTime;
	//TODO: Death-Information if Person was killed during book
	
	private boolean isSuperMainCharapter;
	private boolean isMainCharapter;
	
	private String my_notes;
	
	private ObjectID my_race;
	
	protected PersonInformation(String newName, String newNickname, 
			String newAge, boolean ageBookStart, boolean ageFirstAppearance, boolean isDeathBeforeBookStart, String newDeathTime,
			boolean newIsSuperMainCharapter, boolean newIsMainCharapter,
			String newNotes, ObjectID newRace) {
		my_name = newName;
		my_nickname = newNickname;
		
		my_age = newAge;
		age_startAtBook = ageBookStart;
		age_startAtFirstAppearance = ageFirstAppearance;
		death_isDeathBeforeBookStart = isDeathBeforeBookStart;
		my_deathTime = newDeathTime;
		
		isSuperMainCharapter = newIsSuperMainCharapter;
		isMainCharapter = newIsMainCharapter;
		
		my_notes = newNotes;
		
		my_race = newRace;
	}
	
	public void editInformation(String newName, String newNickname,
			String newAge, boolean ageBookStart, boolean ageFirstAppearance, boolean isDeathBeforeBookStart, String newDeathTime,
			boolean newIsSuperMainCharapter, boolean newIsMainCharapter,
			String newNotes, ObjectID newRace, ObjectID OWNid) {
		my_name = newName;
		my_nickname = newNickname;
		
		my_age = newAge;
		age_startAtBook = ageBookStart;
		age_startAtFirstAppearance = ageFirstAppearance;
		death_isDeathBeforeBookStart = isDeathBeforeBookStart;
		my_deathTime = newDeathTime;
		
		isSuperMainCharapter = newIsSuperMainCharapter;
		isMainCharapter = newIsMainCharapter;
		
		my_notes = newNotes;
		
		Book.getInstance().getSociety().updateRaceRepresantives(my_race, newRace, OWNid);
		my_race = newRace;
		
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
	
	public String getRealNickname() {
		if(my_nickname == null) { return "";}
		if("".equals(my_nickname)) { return "";}
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

	public ObjectID getRace() {
		return my_race;
	}

	public boolean isDeadBeforeBookStart() {
		return death_isDeathBeforeBookStart;
	}

	public String getDeathTime() {
		if(my_deathTime == null) { return "";}
		return my_deathTime;
	}	
}
