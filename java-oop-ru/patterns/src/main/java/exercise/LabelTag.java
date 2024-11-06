package exercise;

// BEGIN
public class LabelTag implements TagInterface {
    private String tag;
    private TagInterface subTag;

    public LabelTag(String tag, TagInterface subTag) {
        this.tag = tag;
        this.subTag = subTag;
    }

    @Override
    public String render() {
        return "<label>" + tag + subTag.render() + "</label>";
    }
}
// END
