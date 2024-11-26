@import exercise.util.NamedRoutes
@import exercise.controller.PostsController
@import exercise.dto.posts.PostPage
@param PostPage page

@template.layout.page(
    content = @`
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>${page.getPost().getName()}</title>
        </head>
        <body>

        <h1>${page.getPost().getName()}</h1>

        <div>
            <p>${page.getPost().getBody()}</p>
        </div>
                <!-- Ссылка для редактирования поста -->
                <a href="${NamedRoutes.editPostPath(page.getPost()
                .getId().toString())}" class="btn btn-primary">Edit Post</a>

        </body>
    `
)