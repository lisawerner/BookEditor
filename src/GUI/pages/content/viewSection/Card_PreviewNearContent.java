package GUI.pages.content.viewSection;

import GUI.bookeditorFrame.BookEditorFrame;
import GUI.components.TextPreview;
import GUI.components.TransparentPanel;
import book.Book;
import book.Chapter;
import book.Section;

import javax.swing.*;

public class Card_PreviewNearContent extends TransparentPanel {
    private static final long serialVersionUID = 1L;

    private final Section my_section;

    public Card_PreviewNearContent(Section section, String previewType) {
        my_section = section;
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        Chapter parentChapter = Book.getInstance().getTableOfContent().getChapter(my_section.getParentChapterID());

        if(previewType.equals(TextPreview.TYPE_PREVIEW_BEFORE)) {
            JButton btn_openPreviousSection = new JButton("^ Go to previous section");
            btn_openPreviousSection.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new Page_ViewSection(my_section, parentChapter)));
            this.add(btn_openPreviousSection);
        }

        TransparentPanel preview = new TextPreview(section.getText(), previewType);
        this.add(preview);

        if(previewType.equals(TextPreview.TYPE_PREVIEW_AFTER)) {
            JButton btn_openPreviousSection = new JButton("v Go to following section");
            btn_openPreviousSection.addActionListener(e -> BookEditorFrame.getInstance().switchBody(new Page_ViewSection(my_section, parentChapter)));
            this.add(btn_openPreviousSection);
        }
    }

}
