package exercise;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;



class ValidationTest {

    @Test
    void testValidate() {
        Address address1 = new Address("Russia", "Ufa", "Lenina", "54", null);
        List<String> result1 = Validator.validate(address1);
        List<String> expected1 = List.of();
        assertThat(result1).isEqualTo(expected1);

        Address address2 = new Address(null, "London", "1-st street", "5", "1");
        List<String> result2 = Validator.validate(address2);
        List<String> expected2 = List.of("country");
        assertThat(result2).isEqualTo(expected2);

        Address address3 = new Address("USA", null, null, null, "1");
        List<String> result3 = Validator.validate(address3);
        List<String> expected3 = List.of("city", "street", "houseNumber");
        assertThat(result3).isEqualTo(expected3);
    }

    // BEGIN
    @Test
    public void testAdvancedValidate() {
        Address address = new Address("Spain", "Madrid", "Main St", "7", "2");

        Map<String, List<String>> invalidFields = Validator.advancedValidate(address);

        assertTrue(invalidFields.isEmpty());
    }
    @Test
    public void testAdvancedValidate_InvalidCountry() {
        Address address = new Address("US", "Texas", "Main St", "7", "2");

        Map<String, List<String>> invalidFields = Validator.advancedValidate(address);

        assertTrue(invalidFields.containsKey("country"));
        assertTrue(invalidFields.get("country").contains("length less than 4"));
    }
    @Test
    public void testAdvancedValidate_MixedInvalidFields() {
        Address address = new Address("US", null, "Street", "7", "2");

        Map<String, List<String>> invalidFields = Validator.advancedValidate(address);

        assertTrue(invalidFields.containsKey("country"));
        assertTrue(invalidFields.get("country").contains("length less than 4"));

        assertTrue(invalidFields.containsKey("city"));
        assertTrue(invalidFields.get("city").contains("can not be null"));
    }
    // END
}
