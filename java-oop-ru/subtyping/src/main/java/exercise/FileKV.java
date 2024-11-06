package exercise;

import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage {
    private String path;

    public FileKV(String path, Map<String, String> dictionary) {
        this.path = path;
        Utils.writeFile(path, Utils.serialize(dictionary));
    }

    private Map<String, String> load() {
        return Utils.deserialize(Utils.readFile(path));
    }

    private void save(Map<String, String> dictionary) {
        Utils.writeFile(path, Utils.serialize(dictionary));
    }

    @Override
    public void set(String key, String value) {
        Map<String, String> dictionary = load();
        dictionary.put(key, value);
        save(dictionary);
    }

    @Override
    public void unset(String key) {
        Map<String, String> dictionary = load();
        dictionary.remove(key);
        save(dictionary);
    }

    @Override
    public String get(String key, String defaultValue) {
        Map<String, String> dictionary = load();
        if (dictionary.containsKey(key)) {
            return dictionary.get(key);
        }
        return defaultValue;
    }

    @Override
    public Map<String, String> toMap() {
        return load();
    }
}
// END
