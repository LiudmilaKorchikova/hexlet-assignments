package exercise;

import io.javalin.Javalin;
import java.util.List;
import java.util.Map;

public final class App {

    private static final List<Map<String, String>> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        // BEGIN
        app.get("/users", ctx -> {
            int page = ctx.queryParam("page") != null ? Integer.parseInt(ctx.queryParam("page")) : 1;
            int per = ctx.queryParam("per") != null ? Integer.parseInt(ctx.queryParam("per")) : 5;


            int start = (page - 1) * per;
            int end = Math.min(start + per, USERS.size());


            List<Map<String, String>> usersPage = USERS.subList(start, end);

            ctx.json(usersPage);
        });
        // END

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
