package org.example.module2;

import org.example.module2.squareAndRectangle.Rectangle;
import org.example.module2.squareAndRectangle.Shape;
import org.example.module2.squareAndRectangle.Square;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

public class ShapeTest {
    static Arguments[] shapes() {
        return new Arguments[]{
                Arguments.of(new Rectangle(), 20, 10, 200.0),
                Arguments.of(new Square(), 20, 20, 400.0)
        };
    }

    @ParameterizedTest
    @MethodSource("shapes")
    void shapeArea(Shape shape, int width, int height, double expectedArea) {
        shape.setWidth(width).setHeight(height);
        assertThat(shape.area()).isEqualTo(expectedArea);
    }
}
