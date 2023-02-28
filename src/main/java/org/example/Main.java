package org.example;

import org.example.oldschool.Shape;
import org.example.oldschool.ShapeType;
import org.example.poly.*;
import org.w3c.dom.css.Rect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<ShapeBase> shapes = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            double val = i;
            if ( i > 50000) {
               val = val / 2.0 ;
            }
            shapes.add(new Circle(val));
            shapes.add(new Square(val * 0.8));
            shapes.add(new Triangle(val, val));
            shapes.add(new Rectangle(val, val * val/2.0));
        }
        ShapeBase[] shapesArray = shapes.toArray(ShapeBase[]::new);

        List<Shape> oldSchoolShapes = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            double val = i;
            if ( i > 50000) {
                val = val / 2.0 ;
            }
            oldSchoolShapes.add(new Shape(ShapeType.Circle, val, 0));
            oldSchoolShapes.add(new Shape(ShapeType.Square, val * 0.8, 0));
            oldSchoolShapes.add(new Shape(ShapeType.Triangle, val, val));
            oldSchoolShapes.add(new Shape(ShapeType.Rectangle, val, val * val/2.0));
        }
        Shape[] oldSchoolShapeArray = oldSchoolShapes.toArray(Shape[]::new);

        System.out.println("======= Starting warmup =======");
        for (int i = 0; i < 10000; i++) {
//            double res1 = calculatePolymorphic(shapes);
            double res9 = calculatePolymorphicArray(shapesArray);
//            double res10 = calculatePolymorphicArray4Unfold(shapesArray);
//            double res5 = calculatePolymorphic4Unfold(shapes);
//            double res2 = calculatePolymorphicFunctional(shapes);
//            double res3 = calculateOldschool(oldSchoolShapes);
            double res8 = calculateOldschoolArray(oldSchoolShapeArray);
//            double res6 = calculateOldschool4Unfold(oldSchoolShapes);
//            double res7 = calculateOldschool4UnfoldArray(oldSchoolShapeArray);
//            double res4 = calculateOldschoolFunctional(oldSchoolShapes);
        }

        System.out.println("======= Starting benchmark =======");
//        long start1 = System.nanoTime();
//        double res1 = calculatePolymorphic(shapes);
//        long end1 = System.nanoTime();

        long start9 = System.nanoTime();
        double res9 = calculatePolymorphicArray(shapesArray);
        long end9 = System.nanoTime();

//        long start10 = System.nanoTime();
//        double res10 = calculatePolymorphicArray4Unfold(shapesArray);
//        long end10 = System.nanoTime();

//        long start11 = System.nanoTime();
//        double res11 = calculatePolymorphicArray8Unfold(shapesArray);
//        long end11 = System.nanoTime();
//
//        long start5 = System.nanoTime();
//        double res5 = calculatePolymorphic4Unfold(shapes);
//        long end5 = System.nanoTime();
//
//        long start2 = System.nanoTime();
//        double res2 = calculatePolymorphicFunctional(shapes);
//        long end2 = System.nanoTime();
//
//        long start3 = System.nanoTime();
//        double res3 = calculateOldschool(oldSchoolShapes);
//        long end3 = System.nanoTime();

        long start8 = System.nanoTime();
        double res8 = calculateOldschoolArray(oldSchoolShapeArray);
        long end8 = System.nanoTime();
//
//        long start6 = System.nanoTime();
//        double res6 = calculateOldschool4Unfold(oldSchoolShapes);
//        long end6 = System.nanoTime();
//
//        long start7 = System.nanoTime();
//        double res7 = calculateOldschool4UnfoldArray(oldSchoolShapeArray);
//        long end7 = System.nanoTime();
//
//        long start4 = System.nanoTime();
//        double res4 = calculateOldschoolFunctional(oldSchoolShapes);
//        long end4 = System.nanoTime();

        System.out.println("===== Calculation results =====");
//        System.out.println("                     normal style: " + (end1 - start1));
        System.out.println("                  normalArr style: " + (end9 - start9));
//        System.out.println("                   normal4U style: " + (end5 - start5));
//        System.out.println("                normal4UArr style: " + (end10 - start10));
//        System.out.println("                normal8UArr style: " + (end11 - start11));
//        System.out.println("          normal Functional style: " + (end2 - start2));
        System.out.println();
        System.out.println("             ======= Oldschool style =======");
//        System.out.println("                  oldschool style: " + (end3 - start3));
        System.out.println("               oldschoolarr style: " + (end8 - start8));
//        System.out.println("                oldschool4U style: " + (end6 - start6));
//        System.out.println("             oldschool4UArr style: " + (end7 - start7));
//        System.out.println("       oldschool Functional style: " + (end4 - start4));
    }

    public static double calculatePolymorphic(List<ShapeBase> shapes) {
        double accum = 0;
        for (int i = 0; i < shapes.size(); i++) {
            accum += shapes.get(i).calculateArea();
        }
        return accum;
    }

    public static double calculatePolymorphicArray(ShapeBase[] shapes) {
        double accum = 0;
        for (int i = 0; i < shapes.length; i++) {
            accum += shapes[i].calculateArea();
        }
        return accum;
    }

    public static double calculatePolymorphicArray4Unfold(ShapeBase[] shapes) {
        double accum = 0;
        double accum1 = 0;
        double accum2 = 0;
        double accum3 = 0;
        for (int i = 0; i < shapes.length; i+=4) {
            accum += shapes[i].calculateArea();
            accum1 += shapes[i + 1].calculateArea();
            accum2 += shapes[i + 2].calculateArea();
            accum3 += shapes[i + 3].calculateArea();
        }
        return accum + accum1 + accum2 + accum3;
    }

    public static double calculatePolymorphicArray8Unfold(ShapeBase[] shapes) {
        double accum = 0;
        double accum1 = 0;
        double accum2 = 0;
        double accum3 = 0;

        int count = 0;
        while(count < shapes.length) {
            accum += shapes[count].calculateArea();
            accum1 += shapes[count + 1].calculateArea();
            accum2 += shapes[count + 2].calculateArea();
            accum3 += shapes[count + 3].calculateArea();
            count += 4;
        }
//        for (int i = 0; i < shapes.length; i+=4) {
//            accum += shapes[i].calculateArea();
//            accum1 += shapes[i + 1].calculateArea();
//            accum2 += shapes[i + 2].calculateArea();
//            accum3 += shapes[i + 3].calculateArea();
//        }
        return accum + accum1 + accum2 + accum3;
    }

    public static double calculatePolymorphic4Unfold(List<ShapeBase> shapes) {
        double accum = 0;
        double accum1 = 0;
        double accum2 = 0;
        double accum3 = 0;
        for (int i = 0; i < shapes.size(); i+=4) {
            accum += shapes.get(i).calculateArea();
            accum1 += shapes.get(i + 1).calculateArea();
            accum2 += shapes.get(i + 2).calculateArea();
            accum3 += shapes.get(i + 3).calculateArea();
        }
        return accum + accum1 + accum2 + accum3;
    }

    public static double calculatePolymorphicFunctional(List<ShapeBase> shapes) {
        return shapes.stream().map(ShapeBase::calculateArea).mapToDouble(Double::doubleValue).sum();
    }

    public static double calculateOldschool(List<Shape> shapes) {
        double accum = 0;
        for (int i = 0; i < shapes.size(); i++) {
            accum += shapes.get(i).calculateArea();
        }
        return accum;
    }

    public static double calculateOldschoolArray(Shape[] shapes) {
        double accum = 0;
        for (int i = 0; i < shapes.length; i++) {
            accum += shapes[i].calculateArea();
        }
        return accum;
    }

    public static double calculateOldschool4Unfold(List<Shape> shapes) {
        double accum = 0;
        double accum1 = 0;
        double accum2 = 0;
        double accum3 = 0;

        for (int i = 0; i < shapes.size(); i+=4) {
            accum += shapes.get(i).calculateArea();
            accum1 += shapes.get(i + 1).calculateArea();
            accum2 += shapes.get(i + 2).calculateArea();
            accum3 += shapes.get(i + 3).calculateArea();
        }
        return accum + accum1 + accum2 + accum3;
    }

    public static double calculateOldschool4UnfoldArray(Shape[] shapes) {
        double accum = 0;
        double accum1 = 0;
        double accum2 = 0;
        double accum3 = 0;

        for (int i = 0; i < shapes.length; i+=4) {
            accum += shapes[i].calculateArea();
            accum1 += shapes[i + 1].calculateArea();
            accum2 += shapes[i + 2].calculateArea();
            accum3 += shapes[i + 3].calculateArea();
        }
        return accum + accum1 + accum2 + accum3;
    }

    public static double calculateOldschoolFunctional(List<Shape> shapes) {
        return shapes.stream().map(Shape::calculateArea).mapToDouble(Double::doubleValue).sum();
    }
}