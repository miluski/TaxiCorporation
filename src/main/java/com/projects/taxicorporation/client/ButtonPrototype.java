package com.projects.taxicorporation.client;

public interface ButtonPrototype {
    ButtonPrototype clone() throws CloneNotSupportedException;
    void setText(String text);
    String getText();
}
