package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public abstract class Tag {
    protected String name;
    protected Map<String, String> attributes;

    public Tag(String name, Map<String, String> attributes) {
        this.name = name;
        this.attributes = attributes;
    }

    protected String getAttributes() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            builder.append(" ")
                    .append(entry.getKey())
                    .append("=")
                    .append("\"")
                    .append(entry.getValue())
                    .append("\"");
        }
        return builder.toString();
    }

    public abstract String toString();
}
// END
