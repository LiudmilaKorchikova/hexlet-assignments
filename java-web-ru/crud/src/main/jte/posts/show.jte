@import exercise.util.NamedRoutes
@import exercise.controller.PostsController
@import exercise.dto.posts.PostPage
@param PostPage page
@param String backLink

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

        <!-- Ссылка для возвращения к списку всех постов -->
        <a href="${backLink}">Back to Posts List</a>

        </body>
    `
)