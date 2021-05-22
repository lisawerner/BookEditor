package book;

import java.util.ArrayList;

import global.ObjectID;
import global.SerializedObject;

public class Chapter extends SerializedObject {

	private String my_name;
	private final ArrayList<Section> my_sections;
	
	public Chapter(String newName) {
		super();
		
		my_name = newName;
		my_sections = new ArrayList<>();
	}
	
	public void addSection(Section newSection) {
		my_sections.add(newSection);
		Book.getInstance().save();
	}

	public String getTitle() {
		return my_name;
	}

	public String getShortTextPreview() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Section> getSections() {
		return my_sections;
	}
	
	public Section getSection(ObjectID sectionID) {
		for(Section section : my_sections) {
			if(section.equals(sectionID)) {
				return section;
			}
		}
		return null;
	}

	public void moveSection(Section moveSection, boolean moveUp) {
		int index = my_sections.indexOf(moveSection);
		my_sections.remove(index);
		if(moveUp) {
			index --;
		} else {
			index ++;
		}
		my_sections.add(index, moveSection);
		Book.getInstance().save();
	}

	public boolean isFirstSection(Section section) {
		return my_sections.indexOf(section) == 0;
	}

	public boolean isLastSection(Section section) {
		return my_sections.indexOf(section) == my_sections.size()-1;
	}

	public void editTitle(String newName) {
		my_name = newName;
		Book.getInstance().save();
	}
	
	public void removeSection(Section section) {
		my_sections.remove(section);
		Book.getInstance().save();
	}

	public int getCountOfWords() {
		int sum = 0;
		for(Section section : my_sections){
			sum += section.getCountWords();
		}
		return sum;
	}
	
}
