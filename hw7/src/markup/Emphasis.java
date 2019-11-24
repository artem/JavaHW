package markup;

import java.util.List;

public class Emphasis extends HtmlElement implements ParagraphElement {
    protected Emphasis(List<ParagraphElement> l) {
        super(l, "em");
    }
    public void toMarkdown(StringBuilder stringBuilder) {
        super.toMarkdown(stringBuilder, "*");
    }
    /*public void toHtml(StringBuilder stringBuilder) {
        //stringBuilder.append("<em>");
        super.toHtml(stringBuilder);
        //stringBuilder.append("</em>");
    }*/
}
