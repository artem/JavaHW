package markup;

import java.util.*;

public class Strikeout extends HtmlElement implements ParagraphElement {
    protected Strikeout(List<ParagraphElement> l) {
        super(l, "s");
    }
    public void toMarkdown(StringBuilder stringBuilder) {
        super.toMarkdown(stringBuilder, "~");
    }
    /*public void toHtml(StringBuilder stringBuilder) {
        //stringBuilder.append("<s>");
        super.toHtml(stringBuilder);
        //stringBuilder.append("</s>");
    }*/
}
