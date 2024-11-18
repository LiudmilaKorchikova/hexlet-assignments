@import exercise.util.NamedRoutes
@import exercise.controller.PostsController
@import exercise.dto.posts.PostsPage
@param PostsPage page
@param Integer currentPage
@param Boolean hasPrev
@param Boolean hasNext

@template.layout.page(
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
                    <a href="${NamedRoutes.postShow(String.valueOf(post.getId()))}">${post.getName()}</a>
                </li>
            @endfor
        </ul>

        <!-- Ссылки для перехода между страницами -->
        <div>
            @if(hasPrev)
                <a href="${NamedRoutes.postsPath()}?page=${currentPage - 1}">Назад</a>
            @endif

            @if(hasNext)
                <a href="${NamedRoutes.postsPath()}?page=${currentPage + 1}">Далее</a>
            @endif
        </div>

        </body>
    `
)
