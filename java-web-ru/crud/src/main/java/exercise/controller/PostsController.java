package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    // BEGIN
    public static void index(Context ctx) {
        var currentPage = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
        int pageSize = 5;

        var posts = PostRepository.findAll(currentPage, pageSize);

        boolean hasPrev = currentPage > 0;

        boolean hasNext = PostRepository.findAll(currentPage + 1, pageSize).size() > 0;

        var page = new PostsPage(posts);
        ctx.render("posts/index.jte", model(
                "page", page,
                "currentPage", currentPage,
                "hasPrev", hasPrev,
                "hasNext", hasNext
        ));
    }

    public static void show(Context ctx) {
        long id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
        var page = new PostPage(post);

        ctx.render("posts/show.jte", model(
                "page", page,
                "backLink", "/posts"
        ));
    }
    // END
}
