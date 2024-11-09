package exercise;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// BEGIN
public class App {

    public static void save(Path path, Car car) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(car);
            Files.writeString(path, json);
        } catch (IOException e) {
            throw new RuntimeException("Error saving object to file", e);
        }
    }

    public static Car extract(Path path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = Files.readString(path);
            return mapper.readValue(json, Car.class);
        } catch (IOException e) {
            throw new RuntimeException("Error extracting object from file", e);
        }
    }
}
// END
