@import io.javalin.rendering.template.JavalinJte
@import exercise.dto.users.UserPage
@param UserPage page

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Details</title>
</head>
<body>
    <h1>User Details</h1>
    <div>
        <h5 class="card-title">${page.getUser().getFirstName()} ${page.getUser().getLastName()}</h5>
        <p class="card-text"><strong>ID:</strong> ${page.getUser().getId()}</p>
        <p class="card-text"><strong>Email:</strong> ${page.getUser().getEmail()}</p>
    </div>
</body>
</html>