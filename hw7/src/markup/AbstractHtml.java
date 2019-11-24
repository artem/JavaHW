package markup;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractHtml implements Html {
    List<Html> list;
    String str;
    public AbstractHtml(List<?extends Html> li, String s) {
        list = new ArrayList<Html>();
        str = s;
        if (li == null) return;
        for (var i : li)
            list.add((AbstractHtml) i);
    }
    /*
    public AbstractHtml(List<ParagraphsAndLists> li) {
        list = new ArrayList<Html>();
        if (li == null) return;
        for (var i : li)
            list.add((AbstractHtml) i);
    }
    public AbstractHtml(List<ListItem> li, boolean f) {
        list = new ArrayList<Html>();
        if (li == null) return;
        for (var i : li)
            list.add((AbstractHtml) i);
    }
    public AbstractHtml(List<ParagraphElement> li, ParagraphElement p) {
        list = new ArrayList<Html>();
        if (li == null) return;
        for (var i : li)
            list.add((AbstractHtml) i);
    }*/
    public void toHtml(StringBuilder stringBuilder) {
        if (!str.isEmpty()) {
            stringBuilder.append("<");
            stringBuilder.append(str);
            stringBuilder.append(">");
        }
        for (var i : list) {
            i.toHtml(stringBuilder);
        }
        if (!str.isEmpty()) {
            stringBuilder.append("</");
            stringBuilder.append(str);
            stringBuilder.append(">");
        }
    }
}
