package org.example.oldschool;


public class Shape {
    public ShapeType type;
    public double width;
    public double height;

    public Shape(ShapeType type, double width, double height) {
        this.type = type;
        this.width = width;
        this.height = height;
    }
    public double calculateArea() {
        return switch (type) {
            case Square -> width * width;
            case Rectangle -> width * height;
            case Circle -> Math.PI * width;
            case Triangle -> 0.5 * width * height;
        };
    }
}
