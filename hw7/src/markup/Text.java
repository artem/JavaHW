package markup;

public class Text extends AbstractHtml implements ParagraphElement {
    private String text;
    public Text(String s) {
        super(null, "");
        text = s;
    }
    public void toMarkdown(StringBuilder stringBuilder) {
        stringBuilder.append(text);
    }

    @Override
    public void toHtml(StringBuilder stringBuilder) {
        stringBuilder.append(text);
    }

    public String getText() {
        return text;
    }
}
