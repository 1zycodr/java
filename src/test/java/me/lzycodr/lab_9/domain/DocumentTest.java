package me.lzycodr.lab_9.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DocumentTest {

    private Document document = new Document(Long.valueOf(1), "");

    @Test
    void getId() {
        assertEquals(1, document.getId());
    }

    @Test
    void getUri() {
        assertEquals("", document.getUri());
    }

    @Test
    void setId() {
        document.setId(Long.valueOf(1));
        assertEquals(1, document.getId());
    }

    @Test
    void setUri() {
        document.setUri("");
        assertEquals("", document.getUri());
    }

    @Test
    void builder() {
        Document document = new Document();
        document = Document.builder().uri("").build();
        assertEquals(Document.builder().uri("").build().getUri(), document.getUri());
    }
}