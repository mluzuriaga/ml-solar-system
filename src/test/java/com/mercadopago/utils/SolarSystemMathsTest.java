package com.mercadopago.utils;

import com.sun.javafx.geom.Point2D;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertFalse;

@Test
public class SolarSystemMathsTest {

    public void getCircumferencePointTest() {

        Point2D point2D1 = SolarSystemMaths.getCircumferencePoint(0, 500);
        assertEquals(point2D1, new Point2D(500, 0));

    }

    public void isLineTest() {

        List<Point2D> points = new ArrayList<>();

        // Linea recta
        points.add(new Point2D(1, 2));
        points.add(new Point2D(-1, 1));
        points.add(new Point2D(-3, 0));
        boolean lineaRecta = SolarSystemMaths.isLine(points);
        assertTrue(lineaRecta);

        points.clear();
        // No linea
        points.add(new Point2D(-2, -1));
        points.add(new Point2D(-1, 1));
        points.add(new Point2D(1, 0));
        boolean lineaNoRecta = SolarSystemMaths.isLine(points);
        assertFalse(lineaNoRecta);

    }

    public void isInsideTest() {

        List<Point2D> points = new ArrayList<>();

        // Dentro del triangulo
        points.add(new Point2D(3, 0));
        points.add(new Point2D(-1, 3));
        points.add(new Point2D(-2, -2));
        boolean isInside = SolarSystemMaths.isInside(points, new Point2D(0, 0));
        assertTrue(isInside);

        points.clear();
        // Fuera del triangulo
        points.add(new Point2D(-3, 0));
        points.add(new Point2D(-1, 3));
        points.add(new Point2D(2, 2));
        boolean isNotInside = SolarSystemMaths.isInside(points, new Point2D(0, 0));
        assertFalse(isNotInside);

    }

}
