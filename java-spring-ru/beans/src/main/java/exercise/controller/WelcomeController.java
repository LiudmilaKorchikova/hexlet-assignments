package exercise.controller;

import exercise.daytime.Daytime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

// BEGIN
@RestController
public class WelcomeController {

    private final Daytime daytime;

    @Autowired
    public WelcomeController(Daytime daytime) {
        this.daytime = daytime;
    }

    @GetMapping("/welcome")
    public String welcome() {
        String timeOfDay = daytime.getName();
        return String.format("It is %s now! Welcome to Spring!", timeOfDay);
    }
}
// END
