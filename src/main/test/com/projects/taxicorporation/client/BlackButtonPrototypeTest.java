package com.projects.taxicorporation.client;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BlackButtonPrototypeTest {

    @Test
    void clone_shouldReturnCloneWithSameProperties() throws CloneNotSupportedException {
        BlackButtonPrototype original = new BlackButtonPrototype();
        original.setText("Original Text");
        original.setWidth(100.0);
        original.setHeight(30.0);
        original.setX(50.0);
        original.setY(20.0);
        BlackButtonPrototype clone = (BlackButtonPrototype) original.clone();
        assertNotSame(original, clone);
        assertEquals(original.getText(), clone.getText());
        assertEquals(original.getWidth(), clone.getWidth());
        assertEquals(original.getHeight(), clone.getHeight());
        assertEquals(original.getX(), clone.getX());
        assertEquals(original.getY(), clone.getY());
    }

    @Test
    void accessors_and_mutators_shouldWorkCorrectly() {
        BlackButtonPrototype button = new BlackButtonPrototype();
        button.setText("Test Text");
        button.setWidth(80.0);
        button.setHeight(25.0);
        button.setX(40.0);
        button.setY(15.0);
        assertEquals("Test Text", button.getText());
        assertEquals(80.0, button.getWidth());
        assertEquals(25.0, button.getHeight());
        assertEquals(40.0, button.getX());
        assertEquals(15.0, button.getY());
    }
}
