package GUI_components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.Box;

import global.UserSettings;

import javax.swing.JButton;

public class TutorialCard extends Card {
	private static final long serialVersionUID = 1L;
	
	private static final String FRONT_TAG = "<html><div style='text-align: left;'><font size=\"4\">     ";
	private static final String BACK_TAG = "</size></div></html>";
	private final int maxSteps = 20;
	private final SimpleLabel lblCardTitle;
	private final SimpleLabel my_hint;
	private final JButton btnDone;

	public TutorialCard(int tutorialNumber, boolean needsConfirmation) {
		setLayout(new BorderLayout(10, 10));
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		add(horizontalStrut, BorderLayout.SOUTH);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		add(verticalStrut, BorderLayout.WEST);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		add(verticalStrut_1, BorderLayout.EAST);
		
		lblCardTitle = new SimpleLabel(FRONT_TAG + "Tutorial Hint  (Step: " + tutorialNumber + " of " + maxSteps + ")" + BACK_TAG);
		add(lblCardTitle, BorderLayout.NORTH);
		
		TransparentPanel my_body = new TransparentPanel();
		add(my_body, BorderLayout.CENTER);
		my_body.setLayout(new BorderLayout(0, 0));
		
		my_hint = new SimpleLabel("<html>" + getTutorial(tutorialNumber) + "</html>");
		my_body.add(my_hint, BorderLayout.CENTER);
		
		btnDone = new JButton("  Done!  ");
		btnDone.addActionListener(e -> done(tutorialNumber));
		if(needsConfirmation) {			
			TransparentPanel panel_placeDoneButton = new TransparentPanel();
			my_body.add(panel_placeDoneButton, BorderLayout.SOUTH);
			
			panel_placeDoneButton.add(btnDone);
		}
		
		changeTheme();
	}

	private void changeTheme() {
		if(ThemeList.currentTheme != null) {
			setBorder(BorderFactory.createLineBorder(ThemeList.currentTheme.warningTutorialCardLayout));
			lblCardTitle.setForeground(ThemeList.currentTheme.headerFontColor);
			lblCardTitle.setBackground(ThemeList.currentTheme.warningTutorialCardLayout);
			lblCardTitle.setOpaque(true);
			btnDone.setBorder(BorderFactory.createRaisedBevelBorder());
			btnDone.setForeground(ThemeList.currentTheme.headerFontColor);
			btnDone.setBackground(ThemeList.currentTheme.warningTutorialCardLayout);
			revalidate();
			repaint();
		} else {
			setBorder(BorderFactory.createLineBorder(Color.RED));
			lblCardTitle.setBorder(BorderFactory.createLineBorder(Color.RED));
			revalidate();
			repaint();
		}
	}
	
	private String getTutorial(int tutorialNumber) {
		switch (tutorialNumber) {
        	case 1:
        		return "<p>Welcome to Tutorial Book-Writer!<br/>"
        				+ "<br/>"
        				+ "For more joy of use you should start by choosing your own Color-Theme. ;)<br/>"
        				+ "Click on 'Book Settings' in left navigation menu.";
        	case 2:
        		return "<p>Choose your favorite Color-Theme for more joy of use and click 'save'.</p>";
        	case 3:
        		return "<p>You have chosen a nice Theme. ;)<br/>"
        				+ "<br/>"
        				+ "Now you can start using Book-Writer.<br/>"
        				+ "Create your first text section.<br/>"
        				+ "For that, click on 'Add new Section' in the left navigation menu.</p>";
        	case 4:
        		return "<p>Create your first text section!<br/>"
        				+ "A Section is a short (or long) text and can later be combined with further sections into a chapter.<br/>"
        				+ "<br/>"
        				+ "First: Choose a short, describing Title for the Section.<br/>"
        				+ "The title is only for overview of the sections in the Table of Contents.<br/>"
        				+ "Then: Add some Notes or a Text. You can also do that later.</p>"
        				+ "Last but not least: Click on 'save'.<br/>"
        				+ "<br/>" 
        				+ "Example: Title 'Prologue'; Text(-Notes): Little Bird flies around; Beautiful flower-meadow; Sunny Day;</p>";
        	case 5:
        		return "<p>Congratulations! You have created your first Section!<br/>"
        				+ "You can see all Sections in the left navigation menu under 'Table of Contents'.<br/>"
        				+ "<br/>"
        				+ "The Book-Writer helps you by getting an overview about the development status of your book.<br>"
        				+ "For that you should directly change the development status of this section.<br/>"
        				+ "Click on the name of your created Section in the Table of Contents in the left navigation menu.</p>";
        	case 6:
        		return "<p>Congratulations! You have created your first Section!<br/>"
        				+ "You can see all Sections in the left navigation menu under 'Table of Contents'.<br/>"
        				+ "<br/>"
        				+ "The Book-Writer helps you by getting an overview about the development status of your book.<br>"
        				+ "For that you should directly change the development Status of this section.<br/>"
        				+ "At the moment you can choose between 'Empty', because you have added nothing in the text field;<br/>"
        				+ "'Only Notes' when you have added some Notes and 'Rework Text' if you have added your first text, which is a beginning concept.<br/>"
        				+ "<br/>"
        				+ "Click on '>> Upgrade >>' to change the development Status of the Section.</p>";
        	case 7:
        		return "<p>Congratulations! You have created your first Section<br/>"
        				+ "and set the development status!<br/>"
        				+ "<br/>"
        				+ "Click on 'Add new Section' again and add some more sections.<br/>"
        				+ "If you are ready we can sort your sections and give the book some structure.<br/>"
        				+ "Click on 'Sort Content' in the left navigation menu for that.</p>";
        	case 8:
        		return "<p>Give your book some structure!<br/>"
        				+ "<br/>"
        				+ "As said before: Sections are only short texts.<br/>"
        				+ "Here you can combine sections to a chapter.<br/>"
        				+ "Therefore you need to make one section the starting text of the chapter and add other sections below it.<br/>"
        				+ "<br/>"
        				+ "For changing a section to start-text click '&lt;'.<br>"
        				+ "You can redo that by clicking '>'."
        				+ "Sort other sections with '^' and 'v' and add them to chapters.</p>";
        	case 9:
        		return "<p>That's great! You can use Book-Writer now for writing and sorting your text.<br/>"
        				+ "<br/>"
        				+ "But there are a lot more helpful functions.<br/>"
        				+ "For example the Timeline. But when you click on 'Timeline' at the moment it is empty.<br/>"
        				+ "You can enter Sections to the Timeline by adding a Timestamp to them.<br/>"
        				+ "Click on a created Section in left navigation menu and then on 'Change' to add a Timestamp!<br>"
        				+ "Do that for every Section or only for Chapters.<br/>"
        				+ "<br/>"
        				+ "After that view Timeline under: 'Timeline' in navigation menu on left side.</p>";
        	case 10:
        		return "<p>When your timeline has been filled with sections you can add additional information.<br/>"
        				+ "Click on 'Persons' in the left navigation menu and add a new Person.</p>";
        	case 11:
        		return "<p>Create a new Person by adding a Name.<br/>"
        				+ "You can also add additional information but that is not necessary.<br/>"
        				+ "Don't forget to click 'Save'.</p>";
        	case 12:
        		return "<p>Nice to meet your fictional characters!<br/>"
        				+ "You can see all characters under 'Persons' in the left navigation menu and then in the list on the right side.<br/>"
        				+ "Click on any name to change information about the character.<br/>"
        				+ "<br/>"
        				+ "Now: Add as many characters as you want under 'Persons' in the left navigation menu and then 'Add new'.</p>";
        	case 13:
        		return "<p>Nice to meet your fictional characters!<br/>"
        				+ "Now tag them to sections.<br/>"
        				+ "Click on a created section in left navigation menu and then click on 'Change' in the Tags menu on the right side.</p>";
        	case 14:
        		return "<p>Very good, you found the Page for tagging Persons to a section!<br/>"
        				+ "Now click on the checkboxes for tagging a Person.<br/>"
        				+ "<br/>"
        				+ "You can also add some information about the relationship of the characters.<br/>"
        				+ "Make your characters to friends, enemies, family or something else.<br/>"
        				+ "You do not need to add relationship-information for every section.<br/>"
        				+ "Add this only then, if the relationship is new or has been changed in the section.<br/>"
        				+ "Don't forget to click 'save'.</p>";
        	case 15:
        		return "<p>View your Timeline again!<br/>"
        				+ "Now you can see the tagged Persons there!<br/>"
        				+ "<br/>"
        				+ "You can also see them here:<br/>"
        				+ " &#8594; 'Persons' in navigation menu  &#8594; Click on a created Person  &#8594; View in one of the boxes in the middle of the page.<br/>"
        				+ "And:  &#8594; Click on a created Section  &#8594; View list on right side.</p>";
        	case 16:
        		return "<p>Well done!<br/>"
        				+ "You can do same with places.<br/>"
        				+ "Click on 'World' in left navigation menu and create some places in your fictional/real world by clicking 'Add Place'.</p>";
        	case 17:
        		return "<p>Very good, you found the Page for creating a new Place!<br/>"
        				+ "<br/>"
        				+ "Now add a new Place by adding a name and a possible Type, such as State, City, Hotel, Room, Park or what ever.<br/>"
        				+ "Click 'Save' if you are ready.</p>";
        	case 18:
        		return "<p>You should add some more Places.<br/>"
        				+ "Then you can set dependencies between them.<br/>"
        				+ "Click on a created Place and set the 'Parent-Place'.<br/>"
        				+ "<br/>"
        				+ "Example: You have a State StarLand and a City ShiningCity. Then click on ShiningCity.<br/>"
        				+ "There you add the Parent StarLand. The Book-Writer knows now that ShiningCity is inside StarLand.<br/>"
        				+ "<br/>"
        				+ "You can view this when you click on 'World' in the navigation menu and then click 'View Map' in the right menu.</p>";
        	case 19:
        		return "<p>When you have created a World with different Places, you can tag them to Sections.<br/>"
        				+ "Click on a created Section and change the Tags.<br/>"
        				+ "<br/>"
        				+ "After that click Timeline again.</p>";
        	case 20:
        		return "<p>Congratulations!<br/>"
        				+ "You can create Sections/Chapters with Text and tag Persons and Places to view them in the Timeline.<br/>"
        				+ "Now you can work with the Book-Writer!"
        				+ "<br/>"
        				+ "But before that, there are still more possibilities you should know for finishing and printing your Stories.<br/>"
        				+ "For example the Filter. It will help you to find Sections, which are not ready for printing.<br/>"
        				+ "Or some helpful Settings, such as Reminder.<br/>"
        				+ "Discover this Application by yourself.<br/>"
        				+ "If you have any questions look in the 'Help', which you can find below in the window, or contact the programmer with 'support'.<br/>"
        				+ "<br/>"
        				+ "And now: Have fun and create your own worlds, characters and stories! :)</p>";
		}
		return "";
	}
	
	private void done(int tutorialNumber) {
		my_hint.setText("<html>Very good!<br/>Click on 'Home' in the left navigation menu for more tips.</html>");
		if(tutorialNumber == maxSteps) {
			my_hint.setText("<html>Congratulations!<br/>The Tutorial is over. Have fun exploring this Application.</html>");
		}
		btnDone.setEnabled(false);
		
		switch (tutorialNumber) {
    	case 8:
    		UserSettings.getInstance().getTutorial().sortSectionsAndChapters = true;
    		break;
    	case 9:
    		UserSettings.getInstance().getTutorial().setTimestamps = true;
    		break;
    	case 12:
    		UserSettings.getInstance().getTutorial().addFurtherPersons = true;
    		break;
    	case 15:
    		UserSettings.getInstance().getTutorial().viewPersons = true;
    		break;
    	case 18:
    		UserSettings.getInstance().getTutorial().setMapDependencies = true;
    		break;
    	case 19:
    		UserSettings.getInstance().getTutorial().tagPlaceAndViewInTimeline = true;
    		break;
    	case 20:
    		UserSettings.getInstance().getTutorial().finishTutorial = true;
    		break;
		}
		
		UserSettings.getInstance().save();
	}

}
