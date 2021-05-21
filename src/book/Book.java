package book;

import global.ObjectID;
import global.SerializedObject;
import notes.GeneralNote;
import person.Relationship;
import person.Society;
import time.Timestuff;
import world.World;

import java.util.ArrayList;

import GUI_components.Theme;
import global.FileManager;

public class Book extends SerializedObject {
	
	private static Book my_instance;
	private String my_filename;
	
	private String my_title;
	private final Content my_tableOfContent;
	
	private final World my_world;
	private final Society my_society;
	private final Timestuff my_timeline;
	
	private ArrayList<GeneralNote> my_notes;
	
	// About Editor-Settings:
	private Theme my_theme;

	// About Print-Settings:
	private boolean isWorktitle;
	private boolean printChapterName;	
	
	private Book() {
		super();
		
		my_title = "";
		isWorktitle = false;
		
		my_filename = "empty.json";
		
		my_tableOfContent = new Content();
		
		my_society = new Society();
		
		my_world = new World();
		
		my_timeline = new Timestuff();
		
		my_notes = new ArrayList<>();
		
		my_theme = null;
	}
	
	public void createNewBook(String newTitle, boolean isNewTitleWorktitle) {
		my_title = newTitle;
		isWorktitle = isNewTitleWorktitle;
		my_filename = newTitle + "_" + my_uID.getIDtoString().substring(0, 13) + ".json";
		save();
	}
	
	public void loadFromFile(String selectedBook) {
		my_instance = (Book) FileManager.loadJSONFile(selectedBook, Book.class);
	}
	
	public static Book getInstance() {
		if(my_instance == null) {
			my_instance = new Book();
		}
		return my_instance;
	}
	
	public void save() {
		if(!"".equals(my_title)) {			
			FileManager.saveJSONFile(my_filename, this);
		}
	}
	
	public String getTitle() {
		return my_title;
	}
	
	public boolean isWorkTitle() {
		return isWorktitle;
	}
	
	public void changeBooktitleSettings(String newTitle, boolean isNewTitleWorktitle) {
		my_title = newTitle;
		isWorktitle = isNewTitleWorktitle;
		save();
	}

	public Theme getTheme() {
		return my_theme;
	}

	public boolean printChapterName() {
		return printChapterName;
	}

	public boolean exportToTXT() {
		StringBuilder book_text = new StringBuilder(my_title + "\n" + "\n");
		int countChapters = 1;
		for(Chapter chapter : my_tableOfContent.getChapters()) {			
			book_text.append("\n" + "\n" + "*******************************************************************************" + "\n");
			if(printChapterName) {
				book_text.append("###").append(chapter.getTitle()).append("###").append("\n").append("\n");
			} else {
				book_text.append("Chapter ").append(countChapters).append("\n").append("\n");
				countChapters++;
			}
			for(Section section : chapter.getSections()) {
				book_text.append(section.getText()).append("\n").append("\n");
			}
		}
		return FileManager.exportTXTfile(my_title + ".txt", book_text.toString());
		
	}
	
	public Relationship getRelationship(ObjectID relID) {
		for(Chapter chapter : my_tableOfContent.getChapters()) {			
			for(Section section : chapter.getSections()) {
				for(Relationship relationship : section.getRelationships()) {
					if(relID.getIDtoString().equals(relationship.getID().getIDtoString())) {
						return relationship;
					}
				}
			}
		}
		return null;
	}

	public Content getTableOfContent() {
		return my_tableOfContent;
	}
	
	public World getWorld() {
		return my_world;
	}
	
	public Society getSociety() {
		return my_society;
	}
	
	public Timestuff getTimeline() {
		return my_timeline;
	}

	public void changePrintSettings(boolean chapterPrintName) {
		printChapterName = chapterPrintName;
		save();
	}

	public void changeBookTheme(Theme newTheme) {
		my_theme = newTheme;
		save();
	}

	public void addNote(GeneralNote generalNote) {
		if(my_notes == null) {my_notes = new ArrayList<>();}
		
		my_notes.add(generalNote);
		save();
	}

	public ArrayList<GeneralNote> getNotes() {
		if(my_notes == null) {return new ArrayList<>();}
		return my_notes;
	}
	
}
