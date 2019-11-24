package markup;

import java.util.List;

public class UnorderedList extends AbstractList {
    UnorderedList(List<ListItem> l) {
        super(l, "ul");
    }
    /*public void toHtml(StringBuilder stringBuilder) {
        //stringBuilder.append("<ul>");
        super.toHtml(stringBuilder);
        //stringBuilder.append("</ul>");
    }*/
}
