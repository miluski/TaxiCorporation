package com.projects.taxicorporation.client;

public class BlackButtonPrototype implements ButtonPrototype {
    public String text;
    @Override
    public ButtonPrototype clone() throws CloneNotSupportedException {
        return (ButtonPrototype) super.clone();
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return this.text;
    }

}
