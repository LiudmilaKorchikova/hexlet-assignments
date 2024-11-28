package exercise.dto.posts;

import java.util.List;
import exercise.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import exercise.dto.BasePage;

// BEGIN
@AllArgsConstructor
@Getter
public class PostsPage extends BasePage{
    private List<Post> posts;

    public PostsPage(List<Post> posts, String flash) {
        this.posts = posts;
        this.flash = flash;
    }
}
// END
