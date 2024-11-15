@import exercise.dto.users.UsersPage
@import exercise.model.User
@param UsersPage page

@template.layout.page(
    content = @`
        <h2>Список пользователей</h2>

        <!-- Форма для фильтрации -->
        <form action="/users" method="get">
            <input type="search" name="term" value="${page.getTerm()}" />
            <input type="submit" value="Search" />
        </form>


        @if(!page.getUsers().isEmpty())
            <h3>Найденные пользователи:</h3>
            <table class="table">
                <thead>
                    <tr>
                        <th>Имя</th>
                        <th>Фамилия</th>
                    </tr>
                </thead>
                <tbody>
                    @for(User user : page.getUsers())
                        <tr>
                            <td>${user.getFirstName()}</td>
                            <td>${user.getLastName()}</td>
                        </tr>
                    @endfor
                </tbody>
            </table>
        @endif
    `
)