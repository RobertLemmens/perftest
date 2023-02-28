package org.example.poly;

public class Circle extends ShapeBase {

    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}
