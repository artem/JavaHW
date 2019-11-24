package markup;

import java.util.List;

public class HtmlElement extends AbstractHtml implements Html {
    protected List <ParagraphElement> list;
    protected HtmlElement(List<ParagraphElement> l, String str) {
    //protected AbstractHtmlElement(List <?extends AbstractHtml> l) {
        super(l, str);
        list = l;
    }

    public void toMarkdown(StringBuilder stringBuilder, String str) {
        stringBuilder.append(str);
        for (ParagraphElement i : list) {
            i.toMarkdown(stringBuilder);
        }
        stringBuilder.append(str);
    }
    /*public void toHtml(StringBuilder stringBuilder) {
        super.toHtml(stringBuilder);
    }*/
}
