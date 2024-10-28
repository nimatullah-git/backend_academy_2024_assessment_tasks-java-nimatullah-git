package org.example.module2.squareAndRectangle;

public class Square implements Shape {
    private int side;

    @Override
    public Square setWidth(int width) {
        this.side = width;
        return this;
    }

    @Override
    public Square setHeight(int height) {
        this.side = height;
        return this;
    }

    @Override
    public double area() {
        return side * side;
    }
}
