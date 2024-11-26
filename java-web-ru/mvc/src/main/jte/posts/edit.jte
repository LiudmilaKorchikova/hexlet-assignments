@import exercise.util.NamedRoutes
@import exercise.dto.posts.EditPostPage
@import io.javalin.validation.ValidationError

@param EditPostPage page

@template.layout.page(
    content = @`
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Edit Post</title>
        </head>
        <body>
            <h1>Edit Post</h1>
            <form action="${NamedRoutes.updatePostPath(page.getId().toString())}" method="post">
                    <label for="name">Title</label>
                    <input type="text" id="name" name="name" value="${page.getName()}" class="form-control" required>
                    @if (page.getErrors() != null && page.getErrors().containsKey("name"))
                            @for (ValidationError<Object> error : page.getErrors().get("name"))
                                <p>${error.getMessage()}</p>
                            @endfor
                    @endif
                    <label for="body">Body</label>
                    <textarea name="body" id="body" class="form-control">${page.getBody()}</textarea>
                    @if (page.getErrors() != null && page.getErrors().containsKey("body"))
                            @for (ValidationError<Object> error : page.getErrors().get("body"))
                                <p>${error.getMessage()}</p>
                            @endfor
                    @endif
                <button type="submit" class="btn btn-primary">Update Post</button>
            </form>
        </body>
    `
)
