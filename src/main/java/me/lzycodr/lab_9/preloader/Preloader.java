package me.lzycodr.lab_9.preloader;

import me.lzycodr.lab_9.domain.Post;
import me.lzycodr.lab_9.repository.PostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Preloader implements CommandLineRunner {
    private final PostRepository postRepository;

    public Preloader(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Post post1 = Post.builder()
                .title("Demo title 1")
                .description("Demo description 2")
                .build();

        Post post2 = Post.builder()
                .title("Demo title 1")
                .description("Demo description 2")
                .build();

        postRepository.saveAll(Arrays.asList(post1, post2));
    }
}
