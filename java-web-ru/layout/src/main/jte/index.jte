@import io.javalin.rendering.template.JavalinJte


@template.layout.page(
    content = @`
        <h1 class="text-center">Добро пожаловать на сайт!</h1>
        <p class="text-center">Здесь вы можете увидеть список пользователей и их подробную информацию.</p>
    `
)