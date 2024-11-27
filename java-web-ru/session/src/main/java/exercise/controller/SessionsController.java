package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.model.User;
import exercise.repository.UsersRepository;
import static exercise.util.Security.encrypt;

import exercise.util.NamedRoutes;
import exercise.util.Security;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class SessionsController {

    // BEGIN
    public static void index(Context ctx) {
        String currentUser = ctx.sessionAttribute("currentUser");
        ctx.render("index.jte", model("name", currentUser));
    }

    public static void build(Context ctx) throws Exception {

        String name = ctx.formParam("name");
        LoginPage loginPage = new LoginPage(name, null);

        ctx.render("build.jte", model("page", loginPage));
    }

    public static void create(Context ctx) throws Exception {
        var name = ctx.formParam("name");
        var password = ctx.formParam("password");

        try {
            var user = UsersRepository.findByName(name)
                    .orElseThrow(() -> new NotFoundResponse());
            var encryptedPassword = encrypt(password);

            if (!user.getPassword().equals(encryptedPassword)) {
                throw new BadRequestResponse();
            }
            ctx.sessionAttribute("currentUser", name);
            ctx.redirect(NamedRoutes.rootPath());
        } catch (Exception e) {
            String errorMessage = "Wrong username or password";
            var loginPage = new LoginPage(name, errorMessage);
            ctx.render("build.jte", model("page", loginPage));
        }
    }

    public static void delete(Context ctx) throws Exception {
        ctx.sessionAttribute("currentUser", null);
        ctx.redirect(NamedRoutes.rootPath());
    }
    // END
}
