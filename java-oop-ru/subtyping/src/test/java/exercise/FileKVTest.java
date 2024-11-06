package exercise;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import com.fasterxml.jackson.databind.ObjectMapper;
// BEGIN
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
// END


class FileKVTest {

    private static Path filepath = Paths.get("src/test/resources/file").toAbsolutePath().normalize();

    @BeforeEach
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(filepath, content, StandardOpenOption.TRUNCATE_EXISTING);
    }

    // BEGIN
    @Test
    void testSetAndGet() {
        KeyValueStorage storage = new FileKV("src/test/resources/file", Map.of("key", "value"));

        assertThat(storage.get("key", "default")).isEqualTo("value");

        storage.set("key", "newValue");
        assertThat(storage.get("key", "default")).isEqualTo("newValue");

        assertThat(storage.get("unknown", "default")).isEqualTo("default");
    }

    @Test
    void testUnset() {
        KeyValueStorage storage = new FileKV("src/test/resources/file", Map.of("key", "value", "key2", "value2"));

        storage.unset("key");
        assertThat(storage.get("key", "default")).isEqualTo("default");

        assertThat(storage.get("key2", "default")).isEqualTo("value2");
    }

    @Test
    void testToMap() {
        KeyValueStorage storage = new FileKV("src/test/resources/file", Map.of("key", "value", "key2", "value2"));

        Map<String, String> expected = Map.of("key", "value", "key2", "value2");
        assertThat(storage.toMap()).isEqualTo(expected);

        storage.set("key3", "value3");
        expected = Map.of("key", "value", "key2", "value2", "key3", "value3");
        assertThat(storage.toMap()).isEqualTo(expected);
    }
    // END
}
