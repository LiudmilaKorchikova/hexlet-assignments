package exercise.dto;

// BEGIN
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GuestCreateDTO {

    @NotBlank(message = "Name must not be empty")
    private String name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email must not be empty")
    private String email;

    @Pattern(regexp = "^\\+\\d{11,13}$", message = "Phone number must start with '+' and contain 11 to 13 digits")
    private String phoneNumber;

    @Pattern(regexp = "^\\d{4}$", message = "Club card number must be exactly 4 digits")
    private String clubCard;

    @Future(message = "Card expiration date must be in the future or today")
    private LocalDate cardValidUntil;

    // Getters and Setters
}
// END
