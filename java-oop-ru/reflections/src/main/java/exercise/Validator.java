package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN
public class Validator {
    public static List<String> validate(Object object){
        List<String> errors = new ArrayList<String>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(NotNull.class)) {
                field.setAccessible(true);
                try {
                    if (field.get(object) == null) {
                        errors.add(field.getName());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
        return errors;
    }

    public static Map<String, List<String>> advancedValidate(Object object) {
        Map<String, List<String>> invalid = new HashMap<>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            List<String> errors = new ArrayList<>();
            field.setAccessible(true);
            try {
                if (field.isAnnotationPresent(NotNull.class)) {
                    if (field.get(object) == null) {
                        errors.add("can not be null");
                    }
                }
                if (field.isAnnotationPresent(MinLength.class)) {
                    String value = (String) field.get(object);
                    MinLength minLength = field.getAnnotation(MinLength.class);
                    if (value != null && value.length() < minLength.minLength()) {
                        errors.add("length less than " + minLength.minLength());
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (!errors.isEmpty()) {
                System.out.println("Field: " + field.getName() + " Errors: " + errors);
                invalid.put(field.getName(), errors);
            }
        }
        return invalid;
    }
}
// END
