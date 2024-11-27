@import exercise.util.NamedRoutes
@import exercise.dto.MainPage

@param String name

@template.layout.page(
    content = @`
        <div class="container">
            <h1>Welcome to the Main Page</h1>

            @if(name != null)
                <!-- Если пользователь аутентифицирован -->
                <p>Hello, ${name}!</p>
                <form action="${NamedRoutes.logoutPath()}" method="post">
                    <button type="submit" class="btn btn-danger">Выйти</button>
                </form>
            @else
                <!-- Если пользователь не аутентифицирован -->
                <p><a href="${NamedRoutes.buildSessionPath()}" class="btn btn-primary">Войти</a></p>
            @endif
        </div>
    `
)