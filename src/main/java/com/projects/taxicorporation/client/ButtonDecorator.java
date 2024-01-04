package com.projects.taxicorporation.client;

import javafx.scene.control.Button;

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
    public String getText() {
        return decoratedButton.getText();
    }

    @Override
    public void setText(String text) {
        decoratedButton.setText(text);
    }

    @Override
    public double getWidth() {
        return decoratedButton.getWidth();
    }

    @Override
    public void setWidth(double width) {
        decoratedButton.setWidth(width);
    }

    @Override
    public double getHeight() {
        return decoratedButton.getHeight();
    }

    @Override
    public void setHeight(double height) {
        decoratedButton.setHeight(height);
    }

    @Override
    public void setX(double x) {
        decoratedButton.setX(x);
    }
    @Override
    public double getX() {
        return decoratedButton.getX();
    }
    @Override
    public void setY(double y) {
        decoratedButton.setY(y);
    }

    @Override
    public double getY() {
        return decoratedButton.getY();
    }
    public abstract void draw(Controller controller, HandleButtonClick handleButtonClick);
}
