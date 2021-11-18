package me.lzycodr.lab_9.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {

    private Post post = new Post(Long.valueOf(1), "", "");

    @Test
    void getId() {
        assertEquals(post.getId(), 1);
    }

    @Test
    void getTitle() {
        assertEquals(post.getTitle(), "");
    }

    @Test
    void getDescription() {
        assertEquals(post.getDescription(), "");
    }

    @Test
    void setId() {
        post.setId(Long.valueOf(1));
        assertEquals(1, post.getId());
    }

    @Test
    void setTitle() {
        post.setTitle("");
        assertEquals("", post.getTitle());
    }

    @Test
    void setDescription() {
        post.setDescription("");
        assertEquals("", post.getDescription());
    }

    @Test
    void builder() {
        Post post = new Post();
        post = Post.builder().title("").build();
        assertEquals(Post.builder().title("").build().getTitle(), post.getTitle());
    }
}