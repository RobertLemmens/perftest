package org.example.poly;

public class Square extends ShapeBase {

    private final double side;

    public Square(double side) {
        this.side = side;
    }
    @Override
    public double calculateArea() {
        return side * side;
    }
}
