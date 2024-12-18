package exercise.controller.users;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
@RequestMapping("/api/users/{userId}/posts")
public class PostsController {
    private List<Post> posts = new ArrayList<>();

    // GET /api/users/{userId}/posts
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getUserPosts(@PathVariable String userId) {
        var userPosts = posts.stream()
                .filter(post -> String.valueOf(post.getUserId()).equals(userId))
                .toList();

        return userPosts;
    }

    // POST /api/users/{userId}/posts
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post createUserPost(@PathVariable String userId, @RequestBody Post newPost) {

        newPost.setUserId(Integer.parseInt(userId));

        posts.add(newPost);

        return newPost;
    }
}
// END
