package GUI_components;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

public class SubmenuList extends TransparentPanel {
	private static final long serialVersionUID = 1L;
	
	private JScrollPane scrollPane;
	private JPanel my_body;

	public SubmenuList() {
		setLayout(new BorderLayout(0, 0));
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		add(rigidArea, BorderLayout.SOUTH);
		
		//TODO: Die ScrollBar wird nicht aktiviert.... Prüfen warum das so ist o.O
		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		scrollPane.setOpaque(false);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		my_body = new JPanel();
		my_body.setLayout(new GridLayout(0, 1, 5, 5));
		
		JViewport viewport = new JViewport();
		viewport.setOpaque(false);
		scrollPane.setViewportView(viewport);
		viewport.setView(my_body);
		
		changeTheme();
	}
	
	private void changeTheme() {
		if(ThemeList.currentTheme != null) {
			scrollPane.setViewportBorder(BorderFactory.createEmptyBorder());
			scrollPane.setBorder(BorderFactory.createEmptyBorder());
			my_body.setBackground(ThemeList.currentTheme.menuBackColor);
			// ...
			revalidate();
			repaint();
		} else {

		}
	}
	
	public void addItem(MenuListButton newListItem) {
		my_body.add(newListItem);
		revalidate();
		repaint();
	}

}
