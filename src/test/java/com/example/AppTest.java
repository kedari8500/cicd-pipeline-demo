package com.example;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class AppTest {
    @Test
    void greetingContainsName() {
        assertTrue(App.greet("Kedar").contains("Kedar"));
    }
}
