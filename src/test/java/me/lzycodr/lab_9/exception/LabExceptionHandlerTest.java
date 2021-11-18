package me.lzycodr.lab_9.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LabExceptionHandlerTest {

    @Test
    void handleException() {
        LabExceptionHandler labExceptionHandler = new LabExceptionHandler();
        labExceptionHandler.handleException(new Exception());
    }
}