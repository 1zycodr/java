package me.lzycodr.lab_9.service;

import javafx.geometry.Pos;
import me.lzycodr.lab_9.domain.Post;
import me.lzycodr.lab_9.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new RuntimeException(String.format("Post not found by id: %s", id)));
    }

    public Post create(Post post) {
        return postRepository.save(post);
    }

    public Post update(Post post) {
        if (!postRepository.findById(post.getId()).isPresent()) {
            throw new RuntimeException("Post not found!");
        }

        return postRepository.save(post);
    }

    public void delete(Long id) {
        Optional<Post> post = postRepository.findById(id);

        if (post.isPresent()) {
            postRepository.delete(post.get());
        } else {
            throw new RuntimeException("Id was not found");
        }
    }
}
