package markup;

import java.util.List;

public class Paragraph extends HtmlElement implements ParagraphsAndLists {
    public Paragraph(List <ParagraphElement> l) {
        super(l, "");
    }
    public void toMarkdown(StringBuilder stringBuilder) {
        super.toMarkdown(stringBuilder, "");
    }
    /*public void toHtml(StringBuilder stringBuilder) {
        super.toHtml(stringBuilder);
    }*/
}
