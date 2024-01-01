package com.projects.taxicorporation.client;

public abstract class ButtonDecorator implements ButtonPrototype {
    protected ButtonPrototype decoratedButton;
    public ButtonDecorator(ButtonPrototype decoratedButton) {
        this.decoratedButton = decoratedButton;
    }
    @Override
    public ButtonPrototype clone() throws CloneNotSupportedException {
        return (ButtonPrototype) super.clone();

    }
    @Override
    public void setText(String text) {
        decoratedButton.setText(text);
    }
    @Override
    public String getText() {
        return decoratedButton.getText();
    }
    public abstract void draw();
}
