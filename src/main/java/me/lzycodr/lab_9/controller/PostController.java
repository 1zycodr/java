package me.lzycodr.lab_9.controller;

import javafx.geometry.Pos;
import me.lzycodr.lab_9.domain.Post;
import me.lzycodr.lab_9.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<Post>> findAll() {
        return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable Long id) {
        Post foundPost =  postService.findById(id);
        return ResponseEntity.ok(foundPost);
    }

    @PostMapping
    public ResponseEntity<Post> post(@RequestBody Post post) {
        return ResponseEntity.ok(postService.create(post));
    }

    @PutMapping
    public ResponseEntity<?> put(@RequestBody Post post) {
        postService.update(post);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        postService.delete(id);
        return ResponseEntity.accepted().build();
    }
}
