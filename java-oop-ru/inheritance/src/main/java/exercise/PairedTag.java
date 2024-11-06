package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {
    protected String body;
    protected List<Tag> tags;

    public PairedTag(String name, Map<String, String> attributes, String body, List<Tag> tags) {
        super(name, attributes);
        this.body = body;
        this.tags = tags;
    }

    private String getSubAttributes() {
        StringBuilder builder = new StringBuilder();
        for (Tag tag : tags) {
            builder.append("<")
                    .append(tag.name)
                    .append(tag.getAttributes())
                    .append(">");
        }
        return builder.toString();
    }

    @Override
    public String toString() {
        String attributes = getAttributes() + ">" + getSubAttributes();
        return String.format("<%s%s%s</%s>", name, attributes, body, name);
    }
}
// END
