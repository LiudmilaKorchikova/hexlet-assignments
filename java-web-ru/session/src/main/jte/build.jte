@import exercise.util.NamedRoutes
@import exercise.dto.LoginPage
@param LoginPage page

@template.layout.page(
    content = @`
        <div class="mx-auto p-4 py-md-5">
            @if(page.getError() != null)
            <p>${page.getError()}</p>
            @endif
            <form action="${NamedRoutes.loginPath()}" method="post">
                <div class="mb-3">
                    <label class="form-label">
                        Имя пользовалетя:
                        <input type="text" class="form-control" name="name" value="" />
                    </label>
                </div>
                <div class="mb-3">
                    <label class="form-label">
                        Пароль:
                        <input type="text" class="form-control" name="password" value="" />
                    </label>
                </div>
                <input type="submit" class="btn btn-primary" value="Сохранить" />
            </form>
        </div>
    `
)