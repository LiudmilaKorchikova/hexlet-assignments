@import exercise.util.NamedRoutes
@import exercise.controller.PostsController
@import exercise.dto.posts.PostsPage
@param PostsPage page

@template.layout.page(
    page = page,
    content = @`
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Posts List</title>
        </head>
        <body>
            <h1>List of Posts</h1>
            <ul>
                <!-- Перебор списка постов -->
                @for(var post : page.getPosts())
                    <li>
                        <!-- Ссылка на конкретный пост -->
                        <a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a>
                    </li>
                @endfor
            </ul>
        </body>
    `
)