package markup;

import java.util.List;

public class OrderedList extends AbstractList {
    OrderedList(List<ListItem> l) {
        super(l, "ol");
    }
    /*public void toHtml(StringBuilder stringBuilder) {
        //stringBuilder.append("<ol>");
        super.toHtml(stringBuilder);
        //stringBuilder.append("</ol>");
    }*/
}
