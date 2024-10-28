package org.example.module2.squareAndRectangle;

public class Rectangle implements Shape {
    protected int width;
    protected int height;

    @Override
    public final Rectangle setWidth(int width) {
        this.width = width;
        return this;
    }

    @Override
    public final Rectangle setHeight(int height) {
        this.height = height;
        return this;
    }

    @Override
    public double area() {
        return width * height;
    }
}
