package exercise.util;

public class NamedRoutes {

    public static String rootPath() {
        return "/";
    }

    public static String postsPath() {
        return "/posts";
    }

    public static String buildPostPath() {
        return "/posts/build";
    }

    public static String postPath(Long id) {
        return postPath(String.valueOf(id));
    }

    public static String postPath(String id) {
        return "/posts/" + id;
    }

    // BEGIN

    // Маршрут для отображения формы редактирования конкретного поста
    public static String editPostPath(String id) {
        return "/posts/" + id + "/edit";  // Генерация URL для редактирования поста по его ID
    }

    // Маршрут для обработки обновления конкретного поста
    public static String updatePostPath(String id) {
        return "/posts/update/" + id;  // Генерация URL для обновления поста по его ID
    }

    // Маршрут для отображения конкретного поста
    public static String showPostPath(String id) {
        return "/posts/show/" + id;
    }
    // END
}
