package markup;

import java.util.List;

public abstract class AbstractList extends AbstractHtml implements ParagraphsAndLists, Html {
    protected List<ListItem> list;
    AbstractList (List<ListItem> l, String str) {
        super(l, str);
    }
    /*public void toHtml(StringBuilder stringBuilder) {
        super.toHtml(stringBuilder);
    }*/
}
