@import exercise.dto.users.UsersPage
@import exercise.model.User
@param UsersPage page
<html>
<body>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Details</th>
                </tr>
            </thead>
        <tbody>
            @for(User user : page.getUsers())
                <tr>
                    <td>${user.getId()}</td>
                    <td>${user.getFirstName()}</td>
                    <td>${user.getLastName()}</td>
                    <td>${user.getEmail()}</td>
                    <td><a href="/users/${user.getId()}">View</a></td>
                </tr>
            @endfor
        </tbody>
        </table>
</body>
</html>