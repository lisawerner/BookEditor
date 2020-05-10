package GUI.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;

public class FrameSubmenu extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private TransparentPanel panel_btnList;
	private TransparentPanel panel_listBody;

	/**
	 * Creates Submenu with a content list
	 * 
	 * @param submenuTitle		 	don't forget the ":" at the end
	 * @param submenuListTitle		don't forget the ":" at the end
	 */
	public FrameSubmenu(String submenuTitle, String submenuListTitle) {
		setLayout(new BorderLayout(5, 5));
		
		//*****************************************************************************		
		TransparentPanel panel_hardCodedPart = new TransparentPanel();
		add(panel_hardCodedPart, BorderLayout.NORTH);
		panel_hardCodedPart.setLayout(new BorderLayout(0, 0));

		TransparentPanel panel_header = new TransparentPanel();
		panel_hardCodedPart.add(panel_header, BorderLayout.NORTH);
		panel_header.setLayout(new BorderLayout(0, 0));
		
		panel_header.add(new MenuSeparator(), BorderLayout.NORTH);
		panel_header.add(new MenuSeparator(), BorderLayout.CENTER);
		panel_header.add(new MenuSubheader(submenuTitle), BorderLayout.SOUTH);
		
		//*****************************************************************************
		TransparentPanel panel_tableOfContent = new TransparentPanel();
		SimpleScrollPane scrollPane = new SimpleScrollPane(panel_tableOfContent);
		this.add(scrollPane, BorderLayout.CENTER);
		
		panel_btnList = new TransparentPanel();
		panel_hardCodedPart.add(panel_btnList, BorderLayout.CENTER);
		panel_btnList.setLayout(new GridLayout(0, 1, 5, 5));
		
		//*****************************************************************************
		TransparentPanel panel_misPlaceList = new TransparentPanel();
		panel_hardCodedPart.add(panel_misPlaceList, BorderLayout.SOUTH);
		panel_misPlaceList.setLayout(new BoxLayout(panel_misPlaceList, BoxLayout.PAGE_AXIS));
		
		panel_misPlaceList.add(Box.createRigidArea(new Dimension(5, 5)));
		
		panel_misPlaceList.add(new MenuSeparator());
		
		panel_misPlaceList.add(new SimpleLabel(submenuListTitle));
		
		panel_tableOfContent.setLayout(new BorderLayout(0, 0));
		
		TransparentPanel panel_listPosition = new TransparentPanel();
		panel_tableOfContent.add(panel_listPosition, BorderLayout.CENTER);
		panel_listPosition.setLayout(new BorderLayout(0, 0));
		
		panel_listBody = new TransparentPanel();
		panel_listPosition.add(panel_listBody, BorderLayout.NORTH);
		panel_listBody.setLayout(new GridLayout(0, 1, 2, 2));
		
	}
	
	/**
	 * Creates Submenu without a content list
	 * @param submenuTitle		 	don't forget the ":" at the end
	 */
	public FrameSubmenu(String submenuTitle) {
		setLayout(new BorderLayout(5, 5));
		
		//*****************************************************************************		
		TransparentPanel panel_hardCodedPart = new TransparentPanel();
		add(panel_hardCodedPart, BorderLayout.NORTH);
		panel_hardCodedPart.setLayout(new BorderLayout(0, 0));

		TransparentPanel panel_header = new TransparentPanel();
		panel_hardCodedPart.add(panel_header, BorderLayout.NORTH);
		panel_header.setLayout(new BorderLayout(0, 0));
		
		panel_header.add(new MenuSeparator(), BorderLayout.NORTH);
		panel_header.add(new MenuSeparator(), BorderLayout.CENTER);
		panel_header.add(new MenuSubheader(submenuTitle), BorderLayout.SOUTH);
		
		//*****************************************************************************

		panel_btnList = new TransparentPanel();
		panel_hardCodedPart.add(panel_btnList, BorderLayout.CENTER);
		panel_btnList.setLayout(new GridLayout(0, 1, 5, 5));
				
	}
	
	public void addButton(MenuButton newButton){
		panel_btnList.add(newButton);
	}
	
	public void addListEntry(MenuListButton newEntry){
		if(panel_listBody != null){			
			panel_listBody.add(newEntry);
		}
	}

}
