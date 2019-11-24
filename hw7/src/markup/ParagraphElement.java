package markup;

public interface ParagraphElement extends Html {
    public void toMarkdown(StringBuilder stringBuilder);
    public void toHtml(StringBuilder stringBuilder);
}
