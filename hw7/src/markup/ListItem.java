package markup;

import java.util.List;

public class ListItem extends AbstractHtml implements Html {
    //List<ParagraphsAndLists> list;
    public ListItem (List<ParagraphsAndLists> l) {
    //public ListItem (List <?extends AbstractHtml> l) {
        super(l, "li");
    }
/*    public void toHtml(StringBuilder stringBuilder) {
        super.toHtml(stringBuilder);
    }*/
}
