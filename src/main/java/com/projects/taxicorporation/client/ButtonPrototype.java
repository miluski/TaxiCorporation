package com.projects.taxicorporation.client;

public interface ButtonPrototype {
    ButtonPrototype clone() throws CloneNotSupportedException;
    void setText(String text);
    String getText();
    void setWidth(double width);
    double getWidth();
    void setHeight(double height);
    double getHeight();
    void setX(double x);
    double getX();
    void setY(double Y);
    double getY();
}
