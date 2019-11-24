package markup;

import java.util.List;

public class Strong extends HtmlElement implements ParagraphElement {
    protected Strong(List<ParagraphElement> l) {
        super(l, "strong");
    }
    public void toMarkdown(StringBuilder stringBuilder) {
        super.toMarkdown(stringBuilder, "__");
    }
    /*public void toHtml(StringBuilder stringBuilder) {
        //stringBuilder.append("<strong>");
        super.toHtml(stringBuilder);
        //stringBuilder.append("</strong>");
    }*/
}
